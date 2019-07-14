/** 
 * Unsafe version of  {@link #SIMDWidth}. 
 */
public static int nSIMDWidth(long struct){
  return UNSAFE.getInt(null,struct + CUdevprop.SIMDWIDTH);
}
