@Override public void setVideoTextureView(TextureView textureView){
  if (this.textureView == textureView) {
    return;
  }
  verifyApplicationThread();
  removeSurfaceCallbacks();
  this.textureView=textureView;
  needSetSurface=true;
  if (textureView == null) {
    setVideoSurfaceInternal(null,true);
    maybeNotifySurfaceSizeChanged(0,0);
  }
 else {
    if (textureView.getSurfaceTextureListener() != null) {
      Log.w(TAG,"Replacing existing SurfaceTextureListener.");
    }
    textureView.setSurfaceTextureListener(componentListener);
    SurfaceTexture surfaceTexture=textureView.isAvailable() ? textureView.getSurfaceTexture() : null;
    if (surfaceTexture == null) {
      setVideoSurfaceInternal(null,true);
      maybeNotifySurfaceSizeChanged(0,0);
    }
 else {
      setVideoSurfaceInternal(new Surface(surfaceTexture),true);
      maybeNotifySurfaceSizeChanged(textureView.getWidth(),textureView.getHeight());
    }
  }
}
