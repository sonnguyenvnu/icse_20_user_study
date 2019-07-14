/** 
 * Unsafe version of  {@link #UserData}. 
 */
public static long nUserData(long struct){
  return memGetAddress(struct + AIFile.USERDATA);
}
