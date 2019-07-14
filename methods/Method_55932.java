/** 
 * Unsafe version of  {@link #blockDimX}. 
 */
public static int nblockDimX(long struct){
  return UNSAFE.getInt(null,struct + CUDA_LAUNCH_PARAMS.BLOCKDIMX);
}
