/** 
 * ????App???
 * @param context     ???
 * @param packageName ??
 * @return ??
 */
public static Intent getLaunchAppIntent(Context context,String packageName){
  return getIntentByPackageName(context,packageName);
}
