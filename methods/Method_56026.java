/** 
 * Unsafe version of  {@link #SIMDWidth(int) SIMDWidth}. 
 */
public static void nSIMDWidth(long struct,int value){
  UNSAFE.putInt(null,struct + CUdevprop.SIMDWIDTH,value);
}
