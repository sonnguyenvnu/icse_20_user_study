/** 
 * Check whether flags is a empty flags
 * @param flags a flags of message to check
 * @return whether the flags is empty
 */
public static boolean isEmptyFlags(Flags flags){
  if (flags == null)   return true;
  Flags.Flag[] systemFlags=flags.getSystemFlags();
  if (systemFlags != null && systemFlags.length > 0) {
    return false;
  }
  String[] userFlags=flags.getUserFlags();
  if (userFlags != null && userFlags.length > 0) {
    return false;
  }
  return true;
}
