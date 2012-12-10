package object
{
	[Bindable]
	[RemoteClass(alias="object.EmailQueue")]
	public class EmailQueue
	{
		public var email_queue_id:int;
		public var fromemail:String;
		public var to:String;
		public var message:String;
		public var subject:String;
		public var status:int=1;
		
		public function EmailQueue()
		{
		}
	}
}