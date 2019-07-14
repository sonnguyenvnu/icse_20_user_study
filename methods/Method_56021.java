/** 
 * Unsafe version of  {@link #maxThreadsPerBlock(int) maxThreadsPerBlock}. 
 */
public static void nmaxThreadsPerBlock(long struct,int value){
  UNSAFE.putInt(null,struct + CUdevprop.MAXTHREADSPERBLOCK,value);
}
