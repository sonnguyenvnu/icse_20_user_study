public void setCurrentFlashMode(String mode){
  currentFlashMode=mode;
  configurePhotoCamera();
  SharedPreferences sharedPreferences=ApplicationLoader.applicationContext.getSharedPreferences("camera",Activity.MODE_PRIVATE);
  sharedPreferences.edit().putString(cameraInfo.frontCamera != 0 ? "flashMode_front" : "flashMode",mode).commit();
}
