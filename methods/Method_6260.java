public boolean hasFrontFaceCamera(){
  ArrayList<CameraInfo> cameraInfos=CameraController.getInstance().getCameras();
  for (int a=0; a < cameraInfos.size(); a++) {
    if (cameraInfos.get(a).frontCamera != 0) {
      return true;
    }
  }
  return false;
}
