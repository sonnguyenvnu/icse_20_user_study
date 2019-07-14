/** 
 * Unsafe version of  {@link #mFlags(int) mFlags}. 
 */
public static void nmFlags(long struct,int value){
  UNSAFE.putInt(null,struct + AIScene.MFLAGS,value);
}
