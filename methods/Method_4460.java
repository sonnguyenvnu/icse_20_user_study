private void startRenderers() throws ExoPlaybackException {
  rebuffering=false;
  mediaClock.start();
  for (  Renderer renderer : enabledRenderers) {
    renderer.start();
  }
}
