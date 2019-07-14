public void switchCamera(){
  if (cameraSession != null) {
    CameraController.getInstance().close(cameraSession,null,null);
    cameraSession=null;
  }
  initied=false;
  isFrontface=!isFrontface;
  initCamera();
}
