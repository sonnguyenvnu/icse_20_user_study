public static boolean isStoragePermissionsGranted(Context context){
  return checkPermissions(context,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
}
