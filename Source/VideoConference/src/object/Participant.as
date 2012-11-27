package object
{
	public class Participant
	{
		[Bindable]
		[RemoteClass(alias="object.Participant")]
		public var participant_id:int;
		public var user_id:int;
		public var conference_id:int;
		public var status:int;
		public function Participant()
		{
		}
	}
}