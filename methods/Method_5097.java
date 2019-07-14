private void removeSurfaceCallbacks(){
  if (textureView != null) {
    if (textureView.getSurfaceTextureListener() != componentListener) {
      Log.w(TAG,"SurfaceTextureListener already unset or replaced.");
    }
 else {
      textureView.setSurfaceTextureListener(null);
    }
    textureView=null;
  }
  if (surfaceHolder != null) {
    surfaceHolder.removeCallback(componentListener);
    surfaceHolder=null;
  }
}
