private void setRepeatModeInternal(@Player.RepeatMode int repeatMode) throws ExoPlaybackException {
  this.repeatMode=repeatMode;
  if (!queue.updateRepeatMode(repeatMode)) {
    seekToCurrentPosition(true);
  }
  handleLoadingMediaPeriodChanged(false);
}
