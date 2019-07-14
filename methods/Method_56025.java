/** 
 * Unsafe version of  {@link #totalConstantMemory(int) totalConstantMemory}. 
 */
public static void ntotalConstantMemory(long struct,int value){
  UNSAFE.putInt(null,struct + CUdevprop.TOTALCONSTANTMEMORY,value);
}
