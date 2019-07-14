@Override public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder){
  verifyApplicationThread();
  if (surfaceHolder != null && surfaceHolder == this.surfaceHolder) {
    setVideoSurfaceHolder(null);
  }
}
