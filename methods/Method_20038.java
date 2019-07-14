/** 
 * Gets the id for the camera specified by the direction it is facing. Returns -1 if no such camera was found.
 * @param facing the desired camera (front-facing or rear-facing)
 */
private static int getIdForRequestedCamera(int facing){
  CameraInfo cameraInfo=new CameraInfo();
  for (int i=0; i < Camera.getNumberOfCameras(); ++i) {
    Camera.getCameraInfo(i,cameraInfo);
    if (cameraInfo.facing == facing) {
      return i;
    }
  }
  return -1;
}
