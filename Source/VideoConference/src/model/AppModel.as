package model
{
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import object.User;

	public class AppModel
	{
		private static var _instance:AppModel;
		public static function getInstance():AppModel
		{
			if(_instance == null){
				_instance = new AppModel();
			}
			return _instance;
		}		
		
		public function navigate(url:String):void
		{
			var urlReq:URLRequest = new URLRequest();
			urlReq.url=url;
			navigateToURL(urlReq,"_self");
		}
	}
}