private void maybeReportPlayerState(){
  if (player == null) {
    return;
  }
  boolean playWhenReady=player.getPlayWhenReady();
  int playbackState=player.getPlaybackState();
  if (lastReportedPlayWhenReady != playWhenReady || lastReportedPlaybackState != playbackState) {
    delegate.onStateChanged(playWhenReady,playbackState);
    lastReportedPlayWhenReady=playWhenReady;
    lastReportedPlaybackState=playbackState;
  }
}
