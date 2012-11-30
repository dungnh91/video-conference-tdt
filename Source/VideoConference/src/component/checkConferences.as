package component
{
	public class checkConferences
	{
		public function checkConferences()
		{
		}
		public static function run(title:String,Description:String,Date:Date,Time_Start:Date,Time_End:Date):Boolean
		{
			if(title==null || title == '')
			{
				Message.show("Please input title",Message.Error,createConference,true);
				return false;
			}
			if(Description == null || Description == '')
			{
				Message.show("Please input Description",Message.Error,createConference,true);
				return false;
			}
			if(Date == null)
			{
				Message.show("Please input Date",Message.Error,createConference,true);
				return false;
			}
			if(Time_Start.time<Time_End.time)
			{
				Message.show("time start must before time end",Message.Error,createConference,true);
				return false;
			}
			return true;
		}
	}
}