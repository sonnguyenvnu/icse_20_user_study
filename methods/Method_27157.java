private static boolean isPermissionGranted(@NonNull Context context,@NonNull String permission){
  return ActivityCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED;
}
