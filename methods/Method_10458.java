/** 
 * This rewrites  {@link #mCameraId} and {@link #mCameraInfo}.
 */
private void chooseCamera(){
  for (int i=0, count=Camera.getNumberOfCameras(); i < count; i++) {
    Camera.getCameraInfo(i,mCameraInfo);
    if (mCameraInfo.facing == mFacing) {
      mCameraId=i;
      return;
    }
  }
  mCameraId=INVALID_CAMERA_ID;
}
