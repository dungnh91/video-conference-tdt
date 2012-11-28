package component
{
	import flash.display.DisplayObject;
	
	import mx.controls.Alert;

	public class checkRegister
	{
		public function checkRegister()
		{
			
		}
		public function run(username:String, password:String, repass:String, email:String, parent:DisplayObject):Boolean
		{
			var pattern:RegExp=/(^[a-zA-Z0-9_\.-]+@[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[a-zA-Z]{2,6})/;
			
			if (username == null || username == '')
			{
				Message.show("Username field is required.",Message.Error,parent,true);
				//Alert.show('Username field is required.', 'Error !!!', Alert.CANCEL);
				return false;
			}
			else if (password == null || password == '')
			{
				Message.show('Password field is required.',Message.Error,parent,true);
				//Alert.show('Password field is required.', 'Error !!!', Alert.CANCEL);
				return false;
			}
			else if (password.length < 8)
			{
				Message.show('Password must contain 8 characters.',Message.Error,parent,true);
				//Alert.show('Password must contain 8 characters','Error !!!', Alert.CANCEL);
				return false;
			}
			else if (repass == null || repass == '')
			{
				Message.show('Please re-type Password.',Message.Error,parent,true);
				//Alert.show('Please re-type Password','Error !!!', Alert.CANCEL);
				return false;
			}
			else if (repass != password)
			{
				Message.show('The re-type password is not correct.',Message.Error,parent,true);
				//Alert.show('The re-type password is not correct','Error !!!', Alert.CANCEL);
				return false;
			}
			else if (email.match(pattern) == null)
			{
				Message.show('Your Email field is required.',Message.Error,parent,true);
				//Alert.show('Your Email field is required.','Error !!!', Alert.CANCEL);
				return false;
			}
			
			return true;
		}
		
	}
}