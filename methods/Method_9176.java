@Override public boolean onSurfaceDestroyed(SurfaceTexture surfaceTexture){
  if (changingTextureView) {
    changingTextureView=false;
    if (inFullscreen || isInline) {
      if (isInline) {
        waitingForFirstTextureUpload=1;
      }
      changedTextureView.setSurfaceTexture(surfaceTexture);
      changedTextureView.setSurfaceTextureListener(surfaceTextureListener);
      changedTextureView.setVisibility(VISIBLE);
      return true;
    }
  }
  return false;
}
