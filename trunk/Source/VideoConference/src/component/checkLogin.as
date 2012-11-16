package component
{
	import mx.controls.Alert;

	public class checkLogin
	{
		public function checkLogin()
		{
			
		}
		
		public function checkUsername(username:String):Boolean
		{
			if (username == null || username == '')
			{
				Alert.show("Bạn phải nhập vào username");
				//return false;
			}
			return true;
		}
		public function checkPassword(password:String):Boolean
		{
			if (password == null || password == '')
			{
				return false;
			}
			return true;
		}
		public function checkRePassword(repass:String, password:String):Boolean
		{
			if (repass == null || repass == '')
			{
				return false;
			}
			else if (repass != password)
			{
				return false;
			}
			return true;
		}
		public function Check_email(email:String):Boolean
		{
			var pattern:RegExp=/(^[a-zA-Z0-9_\.-]+@[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[a-zA-Z]{2,6})/;
			if (pattern.test(email))
				return true;
			else
				return false;
		}
//		public function Check_exist_username(username:String):Boolean
//		{
//			
//		}
	}
}