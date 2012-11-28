package model
{
	public class AppModel
	{
		private var _instance:AppModel; 
		
		public function getInstance():AppModel
		{
			if(_instance == null)
				_instance = new AppModel;
			return _instance;
		}
		public function AppModel()
		{
		}
	}
}