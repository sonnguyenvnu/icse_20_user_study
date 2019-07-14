/** 
 * Unsafe version of  {@link #sharedMemBytes}. 
 */
public static int nsharedMemBytes(long struct){
  return UNSAFE.getInt(null,struct + CUDA_LAUNCH_PARAMS.SHAREDMEMBYTES);
}
