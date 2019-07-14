@Override public void onPlayerStateChanged(boolean playWhenReady,int playbackState){
  maybeReportPlayerState();
  if (playWhenReady && playbackState == Player.STATE_READY) {
    NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.playerDidStartPlaying,this);
  }
  if (!videoPlayerReady && playbackState == Player.STATE_READY) {
    videoPlayerReady=true;
    checkPlayersReady();
  }
}
