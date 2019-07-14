private int calculateCaptureRotation(){
  int captureRotation=0;
  if (mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
    captureRotation=(mCameraInfo.orientation + mDisplayOrientation) % 360;
  }
 else {
    captureRotation=(mCameraInfo.orientation - mDisplayOrientation + 360) % 360;
  }
  if (mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
    captureRotation=((captureRotation - (mDisplayOrientation - mDeviceOrientation)) + 360) % 360;
  }
 else {
    captureRotation=(captureRotation + (mDisplayOrientation - mDeviceOrientation) + 360) % 360;
  }
  return captureRotation;
}
