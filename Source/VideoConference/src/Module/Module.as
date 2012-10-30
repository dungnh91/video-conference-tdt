package Module
{
	public class Module
	{
		public function Module()
		{
		}
		public function Check_email(email:String):Boolean
		{
			var pattern:RegExp=/(^[a-zA-Z0-9_\.-]+@[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[a-zA-Z]{2,6})/;
			if (pattern.test(email))
				return true;
		 	else
				return false;
		}
		public function Check_exist_username(username:String):Boolean
		{
			
		}
	}
}