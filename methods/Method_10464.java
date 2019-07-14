/** 
 * Calculate display orientation https://developer.android.com/reference/android/hardware/Camera.html#setDisplayOrientation(int) <p> This calculation is used for orienting the preview <p> Note: This is not the same calculation as the camera rotation
 * @param screenOrientationDegrees Screen orientation in degrees
 * @return Number of degrees required to rotate preview
 */
private int calcDisplayOrientation(int screenOrientationDegrees){
  if (mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
    return (360 - (mCameraInfo.orientation + screenOrientationDegrees) % 360) % 360;
  }
 else {
    return (mCameraInfo.orientation - screenOrientationDegrees + 360) % 360;
  }
}
