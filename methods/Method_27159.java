private static boolean isReadWritePermissionIsGranted(@NonNull Context context){
  return isPermissionGranted(context,Manifest.permission.READ_EXTERNAL_STORAGE) && isPermissionGranted(context,Manifest.permission.WRITE_EXTERNAL_STORAGE);
}
