@Override public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture){
  if (waitingForFirstTextureUpload == 2) {
    if (textureImageView != null) {
      textureImageView.setVisibility(INVISIBLE);
      textureImageView.setImageDrawable(null);
      if (currentBitmap != null) {
        currentBitmap.recycle();
        currentBitmap=null;
      }
    }
    switchingInlineMode=false;
    delegate.onSwitchInlineMode(controlsView,false,aspectRatioFrameLayout.getAspectRatio(),aspectRatioFrameLayout.getVideoRotation(),allowInlineAnimation);
    waitingForFirstTextureUpload=0;
  }
}
