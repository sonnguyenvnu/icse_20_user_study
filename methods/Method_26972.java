/** 
 * Calculate camera rotation This calculation is applied to the output JPEG either via Exif Orientation tag or by actually transforming the bitmap. (Determined by vendor camera API implementation) Note: This is not the same calculation as the display orientation
 * @param screenOrientationDegrees Screen orientation in degrees
 * @return Number of degrees to rotate image in order for it to view correctly.
 */
private int calcCameraRotation(int screenOrientationDegrees){
  if (mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
    return (mCameraInfo.orientation + screenOrientationDegrees) % 360;
  }
 else {
    final int landscapeFlip=isLandscape(screenOrientationDegrees) ? 180 : 0;
    return (mCameraInfo.orientation + screenOrientationDegrees + landscapeFlip) % 360;
  }
}
