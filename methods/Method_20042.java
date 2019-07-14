/** 
 * Calculates the correct rotation for the given camera id and sets the rotation in the parameters. It also sets the camera's display orientation and rotation.
 * @param parameters the camera parameters for which to set the rotation
 * @param cameraId the camera id to set rotation based on
 */
private void setRotation(Camera camera,Camera.Parameters parameters,int cameraId){
  WindowManager windowManager=(WindowManager)activity.getSystemService(Context.WINDOW_SERVICE);
  int degrees=0;
  int rotation=windowManager.getDefaultDisplay().getRotation();
switch (rotation) {
case Surface.ROTATION_0:
    degrees=0;
  break;
case Surface.ROTATION_90:
degrees=90;
break;
case Surface.ROTATION_180:
degrees=180;
break;
case Surface.ROTATION_270:
degrees=270;
break;
default :
Log.e(TAG,"Bad rotation value: " + rotation);
}
CameraInfo cameraInfo=new CameraInfo();
Camera.getCameraInfo(cameraId,cameraInfo);
int angle;
int displayAngle;
if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
angle=(cameraInfo.orientation + degrees) % 360;
displayAngle=(360 - angle) % 360;
}
 else {
angle=(cameraInfo.orientation - degrees + 360) % 360;
displayAngle=angle;
}
this.rotation=angle / 90;
camera.setDisplayOrientation(displayAngle);
parameters.setRotation(angle);
}
