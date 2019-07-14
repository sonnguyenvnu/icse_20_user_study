private void ensurePleyaerCreated(){
  DefaultLoadControl loadControl=new DefaultLoadControl(new DefaultAllocator(true,C.DEFAULT_BUFFER_SEGMENT_SIZE),DefaultLoadControl.DEFAULT_MIN_BUFFER_MS,DefaultLoadControl.DEFAULT_MAX_BUFFER_MS,100,DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS,DefaultLoadControl.DEFAULT_TARGET_BUFFER_BYTES,DefaultLoadControl.DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS);
  if (player == null) {
    player=ExoPlayerFactory.newSimpleInstance(ApplicationLoader.applicationContext,trackSelector,loadControl,null,DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER);
    player.addListener(this);
    player.setVideoListener(this);
    player.setVideoTextureView(textureView);
    player.setPlayWhenReady(autoplay);
  }
  if (mixedAudio) {
    if (audioPlayer == null) {
      audioPlayer=ExoPlayerFactory.newSimpleInstance(ApplicationLoader.applicationContext,trackSelector,loadControl,null,DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER);
      audioPlayer.addListener(new Player.EventListener(){
        @Override public void onTracksChanged(        TrackGroupArray trackGroups,        TrackSelectionArray trackSelections){
        }
        @Override public void onLoadingChanged(        boolean isLoading){
        }
        @Override public void onTimelineChanged(        Timeline timeline,        Object manifest,        int reason){
        }
        @Override public void onShuffleModeEnabledChanged(        boolean shuffleModeEnabled){
        }
        @Override public void onPositionDiscontinuity(        int reason){
        }
        @Override public void onSeekProcessed(){
        }
        @Override public void onPlayerStateChanged(        boolean playWhenReady,        int playbackState){
          if (!audioPlayerReady && playbackState == Player.STATE_READY) {
            audioPlayerReady=true;
            checkPlayersReady();
          }
        }
        @Override public void onRepeatModeChanged(        int repeatMode){
        }
        @Override public void onPlayerError(        ExoPlaybackException error){
        }
        @Override public void onPlaybackParametersChanged(        PlaybackParameters playbackParameters){
        }
      }
);
      audioPlayer.setPlayWhenReady(autoplay);
    }
  }
}
