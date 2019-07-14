/** 
 * Unsafe version of  {@link #totalConstantMemory}. 
 */
public static int ntotalConstantMemory(long struct){
  return UNSAFE.getInt(null,struct + CUdevprop.TOTALCONSTANTMEMORY);
}
