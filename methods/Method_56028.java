/** 
 * Unsafe version of  {@link #regsPerBlock(int) regsPerBlock}. 
 */
public static void nregsPerBlock(long struct,int value){
  UNSAFE.putInt(null,struct + CUdevprop.REGSPERBLOCK,value);
}
