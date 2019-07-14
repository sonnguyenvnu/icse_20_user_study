/** 
 * Unsafe version of  {@link #sharedMemPerBlock(int) sharedMemPerBlock}. 
 */
public static void nsharedMemPerBlock(long struct,int value){
  UNSAFE.putInt(null,struct + CUdevprop.SHAREDMEMPERBLOCK,value);
}
