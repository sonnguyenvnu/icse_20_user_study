/** 
 * ????App <p>?root?????  {@code <uses-permission android:name="android.permission.INSTALL_PACKAGES" />}</p>
 * @param context  ???
 * @param filePath ????
 * @return {@code true}: ????<br> {@code false}: ????
 */
public static boolean installAppSilent(Context context,String filePath){
  File file=RxFileTool.getFileByPath(filePath);
  if (!RxFileTool.isFileExists(file))   return false;
  String command="LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install " + filePath;
  RxShellTool.CommandResult commandResult=RxShellTool.execCmd(command,!isSystemApp(context),true);
  return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");
}
