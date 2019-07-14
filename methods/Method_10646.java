/** 
 * ??launcher activity
 * @param context     ???
 * @param packageName ??
 * @return launcher activity
 */
public static String getLauncherActivity(Context context,String packageName){
  Intent intent=new Intent(Intent.ACTION_MAIN,null);
  intent.addCategory(Intent.CATEGORY_LAUNCHER);
  PackageManager pm=context.getPackageManager();
  List<ResolveInfo> infos=pm.queryIntentActivities(intent,0);
  for (  ResolveInfo info : infos) {
    if (info.activityInfo.packageName.equals(packageName)) {
      return info.activityInfo.name;
    }
  }
  return "no " + packageName;
}
