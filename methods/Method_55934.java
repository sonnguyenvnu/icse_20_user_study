/** 
 * Unsafe version of  {@link #blockDimZ}. 
 */
public static int nblockDimZ(long struct){
  return UNSAFE.getInt(null,struct + CUDA_LAUNCH_PARAMS.BLOCKDIMZ);
}
