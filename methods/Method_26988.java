/** 
 * <p>Starts opening a camera device.</p> <p>The result will be processed in  {@link #mCameraDeviceCallback}.</p>
 */
private void startOpeningCamera(){
  try {
    mCameraManager.openCamera(mCameraId,mCameraDeviceCallback,null);
  }
 catch (  CameraAccessException e) {
    throw new RuntimeException("Failed to open camera: " + mCameraId,e);
  }
}
