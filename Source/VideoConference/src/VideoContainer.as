package  
{
	import flash.media.Video;
	
	import mx.core.UIComponent;	
	
	public class VideoContainer extends UIComponent
	{
        private var _video:Video;
        
		public function VideoContainer()
		{
			this.height=240;
			this.width=320;
		}
		
        public function set video(video:Video):void
        {
            if (_video != null)
            {
                removeChild(_video);
            }

			_video = video;

           	if (_video != null)
            {
	            _video.width = width;
                _video.height = height;
                addChild(_video);
            }
        }    
	}
}