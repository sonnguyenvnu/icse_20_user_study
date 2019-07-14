protected void configureRecorder(int quality,MediaRecorder recorder){
  Camera.CameraInfo info=new Camera.CameraInfo();
  Camera.getCameraInfo(cameraInfo.cameraId,info);
  int displayOrientation=getDisplayOrientation(info,false);
  int outputOrientation=0;
  if (jpegOrientation != OrientationEventListener.ORIENTATION_UNKNOWN) {
    if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
      outputOrientation=(info.orientation - jpegOrientation + 360) % 360;
    }
 else {
      outputOrientation=(info.orientation + jpegOrientation) % 360;
    }
  }
  recorder.setOrientationHint(outputOrientation);
  int highProfile=getHigh();
  boolean canGoHigh=CamcorderProfile.hasProfile(cameraInfo.cameraId,highProfile);
  boolean canGoLow=CamcorderProfile.hasProfile(cameraInfo.cameraId,CamcorderProfile.QUALITY_LOW);
  if (canGoHigh && (quality == 1 || !canGoLow)) {
    recorder.setProfile(CamcorderProfile.get(cameraInfo.cameraId,highProfile));
  }
 else   if (canGoLow) {
    recorder.setProfile(CamcorderProfile.get(cameraInfo.cameraId,CamcorderProfile.QUALITY_LOW));
  }
 else {
    throw new IllegalStateException("cannot find valid CamcorderProfile");
  }
  isVideo=true;
}
