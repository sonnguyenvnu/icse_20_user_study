/** 
 * For processes to access shared internal storage (/sdcard) we need this permission. 
 */
@TargetApi(Build.VERSION_CODES.M) public boolean ensureStoragePermissionGranted(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
      return true;
    }
 else {
      requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUESTCODE_PERMISSION_STORAGE);
      return false;
    }
  }
 else {
    return true;
  }
}
