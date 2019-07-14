private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
  if (renderer.getState() == Renderer.STATE_STARTED) {
    renderer.stop();
  }
}
