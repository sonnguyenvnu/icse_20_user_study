@Override public void onSurfaceTextureUpdated(SurfaceTexture surface){
  if (!initied && cameraSession != null && cameraSession.isInitied()) {
    if (delegate != null) {
      delegate.onCameraInit();
    }
    initied=true;
  }
}
