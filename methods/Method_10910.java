/** 
 * ????root?????
 * @param command         ??
 * @param isRoot          ??root
 * @param isNeedResultMsg ????????
 * @return CommandResult
 */
public static CommandResult execCmd(String command,boolean isRoot,boolean isNeedResultMsg){
  return execCmd(new String[]{command},isRoot,isNeedResultMsg);
}
