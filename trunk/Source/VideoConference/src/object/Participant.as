package object
{
	[Bindable]
	[RemoteClass(alias="object.Participant")]
	public class Participant
	{
		public var participant_id:int;
		public var host_id:int;
		public var user_id:int;
		public var conference_id:int;
		public var status:int;
		public function Participant()
		{
			status=1;
		}
	}
}