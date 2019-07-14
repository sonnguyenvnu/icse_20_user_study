@Override public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters){
  if (started) {
    resetPosition(getPositionUs());
  }
  this.playbackParameters=playbackParameters;
  return playbackParameters;
}
