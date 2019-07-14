private void switchCamera(){
  saveLastCameraBitmap();
  if (lastBitmap != null) {
    textureOverlayView.setImageBitmap(lastBitmap);
    textureOverlayView.animate().setDuration(120).alpha(1.0f).setInterpolator(new DecelerateInterpolator()).start();
  }
  if (cameraSession != null) {
    cameraSession.destroy();
    CameraController.getInstance().close(cameraSession,null,null);
    cameraSession=null;
  }
  isFrontface=!isFrontface;
  initCamera();
  cameraReady=false;
  cameraThread.reinitForNewCamera();
}
