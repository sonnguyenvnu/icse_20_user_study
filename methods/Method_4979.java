@Override public final void start() throws ExoPlaybackException {
  Assertions.checkState(state == STATE_ENABLED);
  state=STATE_STARTED;
  onStarted();
}
