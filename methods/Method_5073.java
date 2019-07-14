@Override public void clearVideoSurface(Surface surface){
  verifyApplicationThread();
  if (surface != null && surface == this.surface) {
    setVideoSurface(null);
  }
}
