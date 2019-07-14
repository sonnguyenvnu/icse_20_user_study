/** 
 * ?????????
 */
private static boolean isPermission(Activity activity,String permission){
  int permissionCheck=ContextCompat.checkSelfPermission(activity,permission);
  return permissionCheck == PackageManager.PERMISSION_GRANTED;
}
