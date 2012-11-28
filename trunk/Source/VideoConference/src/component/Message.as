package component
{
	import flash.display.DisplayObject;
	
	import mx.controls.Alert;
	import mx.managers.PopUpManager;
	
	import spark.components.BorderContainer;
	import spark.components.HGroup;
	import spark.components.Image;
	import spark.components.VGroup;

	public class Message
	{
		public static var Information:int = 1;
		public static var Warning:int = 2;
		public static var Error:int = 3;
		public function Message()
		{
		}
		public static function show(text:String,type:int,parent:DisplayObject,modal:Boolean):void
		{
			var mess:message = new message();
			mess.body = text;
			switch(type)
			{
				case Information:
					mess.title="Information";
					(((((mess.getElementAt(0) as BorderContainer).getElementAt(0) as VGroup).getElementAt(0) as HGroup).getElementAt(0)) as Image).source = "assets/info.png";
					break;
				case Warning:
					mess.title="Warning";
					(((((mess.getElementAt(0) as BorderContainer).getElementAt(0) as VGroup).getElementAt(0) as HGroup).getElementAt(0)) as Image).source = "assets/warning.png";
					break;
				case Error:
					mess.title="Error";
					(((((mess.getElementAt(0) as BorderContainer).getElementAt(0) as VGroup).getElementAt(0) as HGroup).getElementAt(0)) as Image).source = "assets/error.png";
					break;
			}
			PopUpManager.addPopUp(mess,parent,modal);
			PopUpManager.centerPopUp(mess);
		}
	}
}