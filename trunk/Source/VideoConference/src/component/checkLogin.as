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
				mx.controls.Alert.show('Bạn phải nhập vào Username', 'Lỗi !!!', mx.controls.Alert.CANCEL);
				return false;
			}
			else if (password == null || password == '')
			{
				mx.controls.Alert.show('Bạn phải nhập vào Mật khẩu', 'Lỗi !!!', mx.controls.Alert.CANCEL);
				return false;
			}
			else if (password.length < 8)
			{
				mx.controls.Alert.show('Mật khẩu phải có ít nhất 8 ký tự','Lỗi !!!', mx.controls.Alert.CANCEL);
				return false;
			}
			else if (repass == null || repass == '')
			{
				mx.controls.Alert.show('Bạn hãy nhập lại Mật khẩu','Lỗi !!!', mx.controls.Alert.CANCEL);
				return false;
			}
			else if (repass != password)
			{
				mx.controls.Alert.show('Xác nhận mật khẩu phải giống mật khẩu','Lỗi !!!', mx.controls.Alert.CANCEL);
				return false;
			}
			
			return true;
		}
		
	}
}