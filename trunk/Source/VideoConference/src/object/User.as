package object
{
	[Bindable]
	[RemoteClass(alias="object.User")]
	public class User
	{
		public var user_id:int;
		public var fullname:String;
		public var user_name:String;
		public var password:String;
		public var email:String;
		public var avartar:String;
		public var subcriptions:int;
		public function User()
		{
		}
	}
}