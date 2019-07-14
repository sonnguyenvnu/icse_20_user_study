/** 
 * Unsafe version of  {@link #mPostState}. 
 */
public static int nmPostState(long struct){
  return UNSAFE.getInt(null,struct + AINodeAnim.MPOSTSTATE);
}
