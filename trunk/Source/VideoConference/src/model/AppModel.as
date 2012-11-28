package model
{
	import object.User;

	public class AppModel
	{
		private static var _instance:AppModel;
		[Bindable]private  var user:User;
		public static function getInstance():AppModel
		{
			if(_instance == null){
				_instance = new AppModel();
			}
			return _instance;
		}
		public function getUser():User
		{
			return user;
		}
		public function setUser(user:User):void
		{
			this.user = user;
		}
		public function AppModel()
		{
		}
	}
}