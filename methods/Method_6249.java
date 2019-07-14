public void checkFlashMode(String mode){
  ArrayList<String> modes=CameraController.getInstance().availableFlashModes;
  if (modes.contains(currentFlashMode)) {
    return;
  }
  currentFlashMode=mode;
  configurePhotoCamera();
  SharedPreferences sharedPreferences=ApplicationLoader.applicationContext.getSharedPreferences("camera",Activity.MODE_PRIVATE);
  sharedPreferences.edit().putString(cameraInfo.frontCamera != 0 ? "flashMode_front" : "flashMode",mode).commit();
}
