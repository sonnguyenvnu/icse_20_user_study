/** 
 * Unsafe version of  {@link #memPitch}. 
 */
public static int nmemPitch(long struct){
  return UNSAFE.getInt(null,struct + CUdevprop.MEMPITCH);
}
