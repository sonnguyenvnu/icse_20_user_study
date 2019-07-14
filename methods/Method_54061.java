/** 
 * Unsafe version of  {@link #mPreState(int) mPreState}. 
 */
public static void nmPreState(long struct,int value){
  UNSAFE.putInt(null,struct + AINodeAnim.MPRESTATE,value);
}
