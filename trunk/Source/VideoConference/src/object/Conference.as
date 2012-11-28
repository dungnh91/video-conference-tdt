package object
{
	[Bindable]
	[RemoteClass(alias="object.Conference")]
	public class Conference
	{	
		public var conference_id:int;
		public var int user_id:int;
		public var conference_name:String;
		public var date:Date;
		public var time_start:Date;
		public var time_end:Date;
		public var description:String;
		public var password:String;
		public var participant:int;
		public var status:int;
		
		public function Conference()
		{
		}
	}
}