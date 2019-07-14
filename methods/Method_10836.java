/** 
 * ????????
 * @param context     ???
 * @param packageName ??
 * @return ??
 */
private static Intent getIntentByPackageName(Context context,String packageName){
  return context.getPackageManager().getLaunchIntentForPackage(packageName);
}
