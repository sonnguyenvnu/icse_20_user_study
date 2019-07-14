/** 
 * ???????
 * @param context
 * @param name
 * @return
 */
public static boolean isHavePermission(Context context,String name){
  try {
    return PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(name,context.getPackageName());
  }
 catch (  Exception e) {
  }
  return false;
}
