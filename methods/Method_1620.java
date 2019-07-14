/** 
 * Checks whether the provided status contains a specified flag.
 */
public static boolean statusHasFlag(@Status int status,@Status int flag){
  return (status & flag) == flag;
}
