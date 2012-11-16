package component
{
	import mx.controls.Alert;

	public class checkLogin
	{
		public function checkLogin()
		{
			
		}
		public function run(username:String, password:String, repass:String, email:String):Boolean
		{
			var pattern:RegExp=/(^[a-zA-Z0-9_\.-]+@[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[a-zA-Z]{2,6})/;
			
			if (username == null || username == '')
			{
				Alert.show('Bạn phải nhập vào Username', 'Lỗi !!!', Alert.CANCEL);
				return false;
			}
			else if (password == null || password == '')
			{
				Alert.show('Bạn phải nhập vào Mật khẩu', 'Lỗi !!!', Alert.CANCEL);
				return false;
			}
			else if (password.length < 8)
			{
				Alert.show('Mật khẩu phải có ít nhất 8 ký tự','Lỗi !!!', Alert.CANCEL);
				return false;
			}
			else if (repass == null || repass == '')
			{
				Alert.show('Bạn hãy nhập lại Mật khẩu','Lỗi !!!', Alert.CANCEL);
				return false;
			}
			else if (repass != password)
			{
				Alert.show('Xác nhận mật khẩu phải giống mật khẩu','Lỗi !!!', Alert.CANCEL);
				return false;
			}
			
			return true;
		}
		
	}
}