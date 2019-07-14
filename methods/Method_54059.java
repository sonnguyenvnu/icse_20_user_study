/** 
 * Unsafe version of  {@link #mPreState}. 
 */
public static int nmPreState(long struct){
  return UNSAFE.getInt(null,struct + AINodeAnim.MPRESTATE);
}
