package model
{
	import object.User;

	public class AppModel
	{
		private var _instance:AppModel; 
		private var user:User;
		public function getInstance():AppModel
		{
			if(_instance == null)
				_instance = new AppModel;
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