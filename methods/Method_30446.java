@Override public void onCreate(){
  super.onCreate();
  sInstance=this;
  Resources resources=getResources();
  mMediaDisplayIconMaxSize=resources.getDimensionPixelSize(R.dimen.media_display_icon_max_size);
  mMediaArtMaxSize=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,320,resources.getDisplayMetrics());
  mMediaSourceFactory=new OkHttpMediaSourceFactory();
  mMediaPlayback=new MediaPlayback(this::createMediaSourceFromMediaDescription,this::stopSelf,this);
  mMediaPlayback.getPlayer().addListener(new Player.DefaultEventListener(){
    @Override public void onTimelineChanged(    Timeline timeline,    Object manifest,    int reason){
      EventBusUtils.postAsync(new MusicPlayingStateChangedEvent(PlayMusicService.this));
    }
    @Override public void onPlayerStateChanged(    boolean playWhenReady,    int playbackState){
      EventBusUtils.postAsync(new MusicPlayingStateChangedEvent(PlayMusicService.this));
    }
    @Override public void onPositionDiscontinuity(    int reason){
      EventBusUtils.postAsync(new MusicPlayingStateChangedEvent(PlayMusicService.this));
    }
  }
);
  MediaButtonReceiver.setMediaSessionHost(() -> mMediaPlayback.getMediaSession());
  mMediaNotification=new MediaNotification(this,mMediaPlayback.getMediaSession(),() -> mMediaPlayback.isPlaying(),Notifications.Channels.PLAY_MUSIC.ID,Notifications.Channels.PLAY_MUSIC.NAME_RES,Notifications.Channels.PLAY_MUSIC.DESCRIPTION_RES,Notifications.Channels.PLAY_MUSIC.IMPORTANCE,Notifications.Ids.PLAYING_MUSIC,R.drawable.notification_icon,R.color.douya_primary);
  SharedPrefsUtils.getSharedPrefs().registerOnSharedPreferenceChangeListener(this);
}
