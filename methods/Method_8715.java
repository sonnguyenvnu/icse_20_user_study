public void hideCamera(boolean async){
  destroy(async,null);
  cameraContainer.removeView(textureView);
  cameraContainer.setTranslationX(0);
  cameraContainer.setTranslationY(0);
  textureOverlayView.setTranslationX(0);
  textureOverlayView.setTranslationY(0);
  textureView=null;
}
