/** 
 * Unsafe version of  {@link #blockDimY}. 
 */
public static int nblockDimY(long struct){
  return UNSAFE.getInt(null,struct + CUDA_LAUNCH_PARAMS.BLOCKDIMY);
}
