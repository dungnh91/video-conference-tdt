package
{
	import com.wowza.encryptionAS3.TEA;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.NetStatusEvent;
	import flash.events.SyncEvent;
	import flash.events.TimerEvent;
	import flash.media.*;
	import flash.net.NetConnection;
	import flash.net.NetStream;
	import flash.net.SharedObject;
	import flash.utils.Timer;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Button;
	import mx.controls.Label;
	import mx.controls.List;
	import mx.controls.Text;
	import mx.controls.TextArea;
	import mx.controls.TextInput;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	
	public class AVChatApp extends Application
	{
		public var nc:NetConnection = null;
		private var camera:Camera;
		private var microphone:Microphone;
		private var nsPublish:NetStream= null;
		private var nsPlay:NetStream = null;
		private var myTimer:Timer;
		
		private var streamsSo:SharedObject;
		private var connectedUsersSo:SharedObject;
		
		[Bindable]
		public var streamList:ArrayCollection;
		
		[Bindable]
		public var connectedUsersList:ArrayCollection;
		
		private var sharedSecret:String = "";		
		
		public var streams:List;
		public var subscribeName:TextInput;
		public var publishName:Label;
		//public var connectStr:TextInput
		public var prompt:Text;			
		public var fpsText:Text;
		public var doSubscribe:Boolean;
		public var doPublish:Button;
		public var output:String;
		public var connectionStr:String;
		[Bindable]
		public var videoRemote:Video = new Video(640,480);
		[Bindable]
		public var videoCamera:Video = new Video(320,240);
		
		public function AVChatApp()
		{
			addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		private function init(event:FlexEvent):void
		{
			connectionStr = "rtmp://localhost/avchat";
			doPublish.addEventListener(MouseEvent.CLICK,publish);
			streams.addEventListener(ListEvent.CHANGE,stopFirst);
			doConnect();
			//connectStr.addEventListener(FlexEvent.ENTER,doConnect);			
			
						
			
			startCamera();			
			var myTimer:Timer = new Timer(1000,0);
			myTimer.addEventListener("timer", updateStreamValues);
			myTimer.start();
		}
		private function startCamera():void
		{
			// get the default Flash camera and microphone
			camera = Camera.getCamera();
			microphone = Microphone.getMicrophone();
			
			// here are all the quality and performance settings that we suggest
			camera.setMode(320, 240, 12, false);
			camera.setQuality(0, 75);
			camera.setKeyFrameInterval(29);
			//default rate is 8 Mhz
			microphone.rate=11;
			enablePlayControls(false);		
		}
		
		public function doConnect(event:Event = null):void 
		{
			// connect to the Wowza Media Server			
			if (nc == null) {
			// create a fresh connection to the wowza media server
			nc = new NetConnection();
			nc.addEventListener(NetStatusEvent.NET_STATUS,netStatusHandler);
			
			var client:Object = new Object();		
			client.onBWDone = function():void
			client.onPlayStatus = function():void		
			nc.client = client;
			
			nc.connect(connectionStr);
			} else {
			if (nsPublish != null) {
			nsPublish.attachCamera(null);
			nsPublish.attachAudio(null);
			}
			nsPublish = null;
			
			if (nsPlay != null) {
			nsPlay.attachCamera(null);
			nsPlay.attachAudio(null);
			}
			nsPlay = null;
			
			
			videoCamera.attachCamera(null);
			videoCamera.clear();
			
			videoRemote.attachCamera(null);
			videoRemote.clear();
			
			nc.close();
			nc = null;
			
			enablePlayControls(false);
	
			doSubscribe=true;
			doPublish.label = 'Publish';
			
			prompt.text = "";
			}
			function netStatusHandler(infoObject:NetStatusEvent):void {
			output +="<BR>" + infoObject.info.code;
			if (infoObject.info.code == "NetConnection.Connect.Failed") {
				prompt.text = "Connection failed: Try harder";
				nc = null;
			
			} else if (infoObject.info.code == "NetConnection.Connect.Rejected") {
				prompt.text = "Connection rejected. Sorry";
				nc = null;
			prompt.text = "Connection rejected - not allowed to connect";
			} else if (infoObject.info.code == "NetConnection.Connect.Success") {
				
				//publishName.text = "guest_" + infoObject.info.clientid;
				videoCamera.clear();
				videoCamera.attachCamera(camera);		
				enablePlayControls(true);
				
			output += "<ul>"
			for (var prop:String in infoObject.info)
			{	
				output += "<li>" +prop+":\t"+infoObject.info[prop] + "</li>"
				trace("\t"+prop+":\t"+infoObject.info[prop]);
			}
			output+= "</ul>"
		
			if (infoObject.info.secureToken != undefined)
				nc.call("secureTokenResponse", null, TEA.decrypt(infoObject.info.secureToken, sharedSecret));
			prompt.text = "Connected";
			
			streamsSo = SharedObject.getRemote("streamsSO", nc.uri, false);
			streamsSo.addEventListener(SyncEvent.SYNC, streamSynch);
			streamsSo.connect(nc);		
			
			connectedUsersSo = SharedObject.getRemote("connectedUsersSO", nc.uri, false);
			connectedUsersSo.addEventListener(SyncEvent.SYNC, connectedUsersSynch);
			connectedUsersSo.connect(nc);
			
			} else if (infoObject.info.code == "NetConnection.Connect.Closed") {
			prompt.text = "Connection closed";
			}
			}
		}
		private function streamSynch(event:SyncEvent):void
		{
			var results:Object = event.target.data;
                var array:Array = new Array();
                for( var item:String in results ) {              
                    var obj:Object = new Object();
                    obj.label = results[ item ];
                    obj.value = item;
                    array.push( obj );
                }
           streamList = new ArrayCollection(array);           
		}
		
		private function connectedUsersSynch(event:SyncEvent):void
		{
			var results:Object = event.target.data;
            var array:Array = new Array();
            for( var item:String in results ) 
            {              
                var obj:Object = new Object();
                obj.label = item.replace("c","");
                obj.value = results[ item ];
                array.push( obj );
            }
           connectedUsersList = new ArrayCollection(array);
           
		}
		private function enablePlayControls(isEnable:Boolean):void 
		{
			trace("set control");
			doPublish.enabled = isEnable;
			publishName.enabled = isEnable;
			streams.enabled = isEnable;			
		}
		
		//function to monitor the frame rate and buffer length
		private function updateStreamValues(event:TimerEvent):void
		{
			if (nsPlay != null) 
			{
				fpsText.text = "FPS: " + (Math.round(nsPlay.currentFPS*1000)/1000);
			} else {
				fpsText.text = "FPS: 0";
			}
		}
		
		private function stopFirst(event:Event = null):void
		{
			doSubscribe=true;
			subscribe();
		}
		
		private function subscribe(event:Event = null):void {
			if (streamList.length==0 || nc == null || nc.connected==false)
				return;
				
		if (doSubscribe) {
		// create a new NetStream object for video playback
			nsPlay = new NetStream(nc);
			// trace the NetStream status information
			nsPlay.addEventListener(NetStatusEvent.NET_STATUS, netStreamStatusHandler);
			// set the buffer time to zero since it is chat
			nsPlay.bufferTime=0;
			nsPlay.client = new StreamClient();// client;
			// subscribe to the named stream
			// nsPlay.play(subscribeName.text);
			var thestream:String;
			if (streams.selectedIndex == -1)
			{
				streams.selectedIndex=0;
				thestream = streamList.getItemAt(0).label;
			} else {
				thestream = streams.selectedItem.label;	
			}
			
			nsPlay.play(thestream);
			
			// attach to the stream
			videoRemote.attachNetStream(nsPlay);
			//videoRemote.attachAudio(nsPlay);
			
			doSubscribe=false;;
			} else {
			// here we are shutting down the connection to the server
			videoRemote.attachNetStream(null);
			//videoRemote.attachAudio(null);
			nsPlay.play(null);
			nsPlay.close();
			
			videoRemote.clear();
			streams.selectedIndex = -1;
			doSubscribe=true;
			}
		}
		private function onPlayStatus(infoObject:Object):void
		{
			output += "<br>OnPlayStatus:<br>" + infoObject.code;
				var prop:String;
				output += "<ul>";
				for (prop in infoObject)
				{
					output+= "<li>" + infoObject[prop]  +"</li>";
					trace("\t"+prop+":\t"+infoObject[prop]);
				}
				output+= "</ul>";	
		}
		private function netStreamStatusHandler(event:NetStatusEvent):void 
		{
			output+= "<BR>NetStatus: " + event.info.code;
			if (event.info.code == "NetStream.Seek.Failed" || event.info.code == "NetStream.Play.Failed") {
			} else if (event.info.code == "NetStream.Play.Start") {
			
			} else {
			}
			}
			
			private function publish(event:MouseEvent):void {
			if (doPublish.label == 'Publish') {
			// create a new NetStream object for video publishing
			nsPublish = new NetStream(nc);
			nsPublish.addEventListener(NetStatusEvent.NET_STATUS,netStreamStatusHandler);
			
			// set the buffer time to zero since it is chat
			nsPublish.bufferTime=0;		
			// attach the camera and microphone to the server
			nsPublish.attachCamera(camera);
			nsPublish.attachAudio(microphone);
			
			nsPublish.publish(publishName.text);
			
			doPublish.label = 'Stop';
			} else {
			// here we are shutting down the connection to the server
			nsPublish.attachCamera(null);
			nsPublish.attachAudio(null);
			nsPublish.publish(null);
			nsPublish.close();		
			doPublish.label = 'Publish';
			}
		}		
	}
}
	
	
class StreamClient {   
    public function onMetaData(event:Object):void
    {
    	trace("onMetaData");	
    }
 }