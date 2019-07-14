private boolean shouldShowControllerIndefinitely(){
  if (player == null) {
    return true;
  }
  int playbackState=player.getPlaybackState();
  return (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_ENDED || !player.getPlayWhenReady());
}
