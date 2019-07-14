private int calculatePreviewRotation(){
  if (mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
    return (360 - ((mCameraInfo.orientation + mDisplayOrientation) % 360)) % 360;
  }
 else {
    return (mCameraInfo.orientation - mDisplayOrientation + 360) % 360;
  }
}
