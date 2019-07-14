private boolean hasPermission(){
  Log.d(TAG,"hasPermission");
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    return checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
  }
  return true;
}
