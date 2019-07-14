/** 
 * ??App???root??
 * @return {@code true}: ?<br> {@code false}: ?
 */
public static boolean isAppRoot(){
  RxShellTool.CommandResult result=RxShellTool.execCmd("echo root",true);
  if (result.result == 0) {
    return true;
  }
  if (result.errorMsg != null) {
    Log.d("isAppRoot",result.errorMsg);
  }
  return false;
}
