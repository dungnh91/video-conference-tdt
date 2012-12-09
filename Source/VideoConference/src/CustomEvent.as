package
{
	import flash.events.Event;

	public class CustomEvent extends Event
	{
		public static const Streams:String = "START_STREAM";
		public var _data:Object;

		public function CustomEvent(type:String, data:Object=null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			_data=data;
		}
	}
}