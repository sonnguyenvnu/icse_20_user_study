/** 
 * Unsafe version of  {@link #sharedMemPerBlock}. 
 */
public static int nsharedMemPerBlock(long struct){
  return UNSAFE.getInt(null,struct + CUdevprop.SHAREDMEMPERBLOCK);
}
