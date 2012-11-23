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
				Alert.show('Bạn phải nhập vào Tên đăng nhập', 'Lỗi !!!', Alert.CANCEL);
				return false;
			}
			else if (password == null || password == '')
			{
				Alert.show('Bạn phải nhập vào Mật khẩu', 'Lỗi !!!', Alert.CANCEL);
				return false;
			}
		}
	}
}