package model
{
	import object.User;

	public class AppModel
	{
		private static var _instance:AppModel;
		private static var user:User;
		[Bindable] public var tmp:User;
		public static function getInstance():AppModel
		{
			if(_instance == null){
				_instance = new AppModel();
			}
			return _instance;
		}
		public  static function getUser():User
		{
			return user;
		}
		public static  function setUser(newuser:User):void
		{
			user = newuser;
		}
		public function AppModel()
		{
		}
	}
}