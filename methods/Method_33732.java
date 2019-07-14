/** 
 * ?????????
 */
private static boolean isPermission(Activity activity,String permission,String permission2){
  int permissionCheck=ContextCompat.checkSelfPermission(activity,permission);
  int permissionCheck2=ContextCompat.checkSelfPermission(activity,permission2);
  return permissionCheck == PackageManager.PERMISSION_GRANTED && permissionCheck2 == PackageManager.PERMISSION_GRANTED;
}
