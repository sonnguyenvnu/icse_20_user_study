/** 
 * ??App???Debug??
 * @param context     ???
 * @param packageName ??
 * @return {@code true}: ?<br> {@code false}: ?
 */
public static boolean isAppDebug(Context context,String packageName){
  if (RxDataTool.isNullString(packageName))   return false;
  try {
    PackageManager pm=context.getPackageManager();
    ApplicationInfo ai=pm.getApplicationInfo(packageName,0);
    return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
  }
 catch (  PackageManager.NameNotFoundException e) {
    e.printStackTrace();
    return false;
  }
}
