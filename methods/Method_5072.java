@Override public void clearVideoSurface(){
  verifyApplicationThread();
  setVideoSurface(null);
}
