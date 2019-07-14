/** 
 * Unsafe version of  {@link #regsPerBlock}. 
 */
public static int nregsPerBlock(long struct){
  return UNSAFE.getInt(null,struct + CUdevprop.REGSPERBLOCK);
}
