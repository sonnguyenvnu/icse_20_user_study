public static boolean checkPermissions(Context context,String... permissions){
  for (  String permission : permissions) {
    if (!checkPermission(context,permission)) {
      return false;
    }
  }
  return true;
}
