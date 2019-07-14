/** 
 * Checks whether the provided status contains any of the specified flags.
 */
public static boolean statusHasAnyFlag(@Status int status,@Status int flag){
  return (status & flag) != 0;
}
