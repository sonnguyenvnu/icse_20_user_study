/** 
 * ??App????
 * @param context     ???
 * @param packageName ??
 */
public static void getAppDetailsSettings(Context context,String packageName){
  if (RxDataTool.isNullString(packageName))   return;
  context.startActivity(RxIntentTool.getAppDetailsSettingsIntent(packageName));
}
