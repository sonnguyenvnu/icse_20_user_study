/** 
 * Unsafe version of  {@link #memPitch(int) memPitch}. 
 */
public static void nmemPitch(long struct,int value){
  UNSAFE.putInt(null,struct + CUdevprop.MEMPITCH,value);
}
