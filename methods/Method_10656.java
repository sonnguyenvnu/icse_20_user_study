/** 
 * ??App??
 * @param context     ???
 * @param packageName ??
 * @return App??
 */
public static String getAppName(Context context,String packageName){
  if (RxDataTool.isNullString(packageName))   return null;
  try {
    PackageManager pm=context.getPackageManager();
    PackageInfo pi=pm.getPackageInfo(packageName,0);
    return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
  }
 catch (  PackageManager.NameNotFoundException e) {
    e.printStackTrace();
    return null;
  }
}
