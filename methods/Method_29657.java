@Override boolean frontCameraOnly(){
  Camera.CameraInfo cameraInfo=new Camera.CameraInfo();
  Camera.getCameraInfo(0,cameraInfo);
  boolean isFrontCameraOnly=(Camera.getNumberOfCameras() == 1 && cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT);
  return isFrontCameraOnly;
}
