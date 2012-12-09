package object
{
	[Bindable]
	[RemoteClass(alias="object.EmailQueue")]
	public class EmailQueue
	{
		public var email_queue_id:int;
		public var from:String;
		public var to:String;
		public var message:String;
		public var subject:String;
		public var headers:String;
		public var cc:String;
		public var bcc:String;
		public var descriptions:String;
		public var ip_add:String;
		public var status:int;
		
		public function EmailQueue()
		{
		}
	}
}