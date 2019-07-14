private void requestStoragePermissions(){
  if (mHasStoragePermissions) {
    return;
  }
  ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
}
