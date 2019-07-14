private static boolean checkPermission(Context context,String permission){
  return ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED;
}
