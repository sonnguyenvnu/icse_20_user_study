/** 
 * ??App????
 * @param context     ???
 * @param packageName ??
 * @return {@code true}: ???<br> {@code false}: ???
 */
public static boolean isInstallApp(Context context,String packageName){
  return !RxDataTool.isNullString(packageName) && RxIntentTool.getLaunchAppIntent(context,packageName) != null;
}
