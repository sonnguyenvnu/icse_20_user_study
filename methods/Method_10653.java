/** 
 * ????App <p>?root?????  {@code <uses-permission android:name="android.permission.DELETE_PACKAGES" />}</p>
 * @param context     ???
 * @param packageName ??
 * @param isKeepData  ??????
 * @return {@code true}: ????<br> {@code false}: ????
 */
public static boolean uninstallAppSilent(Context context,String packageName,boolean isKeepData){
  if (RxDataTool.isNullString(packageName))   return false;
  String command="LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall " + (isKeepData ? "-k " : "") + packageName;
  RxShellTool.CommandResult commandResult=RxShellTool.execCmd(command,!isSystemApp(context),true);
  return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");
}
