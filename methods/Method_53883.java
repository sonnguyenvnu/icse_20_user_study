/** 
 * Unsafe version of  {@link #user(long) user}. 
 */
public static void nuser(long struct,long value){
  memPutAddress(struct + AILogStream.USER,check(value));
}
