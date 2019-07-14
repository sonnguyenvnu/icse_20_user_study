private static boolean hasExternalStoragePermission(Context context){
  int perm=context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
  return perm == PackageManager.PERMISSION_GRANTED;
}
