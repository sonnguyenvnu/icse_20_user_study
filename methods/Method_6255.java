protected void configurePhotoCamera(){
  try {
    Camera camera=cameraInfo.camera;
    if (camera != null) {
      Camera.CameraInfo info=new Camera.CameraInfo();
      Camera.Parameters params=null;
      try {
        params=camera.getParameters();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      Camera.getCameraInfo(cameraInfo.getCameraId(),info);
      int displayOrientation=getDisplayOrientation(info,true);
      int cameraDisplayOrientation;
      if ("samsung".equals(Build.MANUFACTURER) && "sf2wifixx".equals(Build.PRODUCT)) {
        cameraDisplayOrientation=0;
      }
 else {
        int degrees=0;
        int temp=displayOrientation;
switch (temp) {
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
}
if (info.orientation % 90 != 0) {
info.orientation=0;
}
if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
temp=(info.orientation + degrees) % 360;
temp=(360 - temp) % 360;
}
 else {
temp=(info.orientation - degrees + 360) % 360;
}
cameraDisplayOrientation=temp;
}
camera.setDisplayOrientation(currentOrientation=cameraDisplayOrientation);
if (params != null) {
params.setPreviewSize(previewSize.getWidth(),previewSize.getHeight());
params.setPictureSize(pictureSize.getWidth(),pictureSize.getHeight());
params.setPictureFormat(pictureFormat);
String desiredMode=Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE;
if (params.getSupportedFocusModes().contains(desiredMode)) {
params.setFocusMode(desiredMode);
}
int outputOrientation=0;
if (jpegOrientation != OrientationEventListener.ORIENTATION_UNKNOWN) {
if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
outputOrientation=(info.orientation - jpegOrientation + 360) % 360;
}
 else {
outputOrientation=(info.orientation + jpegOrientation) % 360;
}
}
try {
params.setRotation(outputOrientation);
if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
sameTakePictureOrientation=(360 - displayOrientation) % 360 == outputOrientation;
}
 else {
sameTakePictureOrientation=displayOrientation == outputOrientation;
}
}
 catch (Exception e) {
}
params.setFlashMode(currentFlashMode);
try {
camera.setParameters(params);
}
 catch (Exception e) {
}
if (params.getMaxNumMeteringAreas() > 0) {
meteringAreaSupported=true;
}
}
}
}
 catch (Throwable e) {
FileLog.e(e);
}
}
