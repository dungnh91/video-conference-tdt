package component
{
	import mx.controls.Alert;
	
	public class checkLogin
	{
		public function checkLogin()
		{
		}
		public function run(username:String, password:String):Boolean
		{
			if (username == null || username == '')
			{
				Alert.show('Please input username', 'Error !!!', Alert.CANCEL);
				return false;
			}
			else if (password == null || password == '')
			{
				Alert.show('Please input password', 'Lỗi !!!', Alert.CANCEL);
				return false;
			}
			return true;
		}
	}
}