@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  setAlpha(0.0f);
  switchCameraButton.setAlpha(0.0f);
  cameraContainer.setAlpha(0.0f);
  textureOverlayView.setAlpha(0.0f);
  muteImageView.setAlpha(0.0f);
  muteImageView.setScaleX(1.0f);
  muteImageView.setScaleY(1.0f);
  cameraContainer.setScaleX(0.1f);
  cameraContainer.setScaleY(0.1f);
  textureOverlayView.setScaleX(0.1f);
  textureOverlayView.setScaleY(0.1f);
  if (cameraContainer.getMeasuredWidth() != 0) {
    cameraContainer.setPivotX(cameraContainer.getMeasuredWidth() / 2);
    cameraContainer.setPivotY(cameraContainer.getMeasuredHeight() / 2);
    textureOverlayView.setPivotX(textureOverlayView.getMeasuredWidth() / 2);
    textureOverlayView.setPivotY(textureOverlayView.getMeasuredHeight() / 2);
  }
  try {
    if (visibility == VISIBLE) {
      ((Activity)getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
 else {
      ((Activity)getContext()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
