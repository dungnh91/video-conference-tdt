package component
{
	import mx.controls.Alert;

	public class checkRegister
	{
		public function checkRegister()
		{
			
		}
		public function run(username:String, password:String, repass:String, email:String):Boolean
		{
			var pattern:RegExp=/(^[a-zA-Z0-9_\.-]+@[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[a-zA-Z]{2,6})/;
			
			if (username == null || username == '')
			{
				Alert.show('Username field is required.', 'Error !!!', Alert.CANCEL);
				return false;
			}
			else if (password == null || password == '')
			{
				Alert.show('Password field is required.', 'Error !!!', Alert.CANCEL);
				return false;
			}
			else if (password.length < 8)
			{
				Alert.show('Password must contain 8 characters','Error !!!', Alert.CANCEL);
				return false;
			}
			else if (repass == null || repass == '')
			{
				Alert.show('Please re-type Password','Error !!!', Alert.CANCEL);
				return false;
			}
			else if (repass != password)
			{
				Alert.show('The re-type password is not correct','Error !!!', Alert.CANCEL);
				return false;
			}
			else if (email.match(pattern) == null)
			{
				Alert.show('Your Email field is required.','Error !!!', Alert.CANCEL);
				return false;
			}
			
			return true;
		}
		
	}
}