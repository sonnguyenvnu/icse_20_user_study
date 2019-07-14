/** 
 * Unsafe version of  {@link #mPostState(int) mPostState}. 
 */
public static void nmPostState(long struct,int value){
  UNSAFE.putInt(null,struct + AINodeAnim.MPOSTSTATE,value);
}
