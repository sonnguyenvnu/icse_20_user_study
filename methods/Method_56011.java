/** 
 * Unsafe version of  {@link #maxThreadsPerBlock}. 
 */
public static int nmaxThreadsPerBlock(long struct){
  return UNSAFE.getInt(null,struct + CUdevprop.MAXTHREADSPERBLOCK);
}
