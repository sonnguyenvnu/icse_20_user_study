private static boolean isExplanationNeeded(@NonNull Activity context,@NonNull String permissionName){
  return ActivityCompat.shouldShowRequestPermissionRationale(context,permissionName);
}
