@Override public final void stop() throws ExoPlaybackException {
  Assertions.checkState(state == STATE_STARTED);
  state=STATE_ENABLED;
  onStopped();
}
