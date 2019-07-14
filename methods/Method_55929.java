/** 
 * Unsafe version of  {@link #gridDimX}. 
 */
public static int ngridDimX(long struct){
  return UNSAFE.getInt(null,struct + CUDA_LAUNCH_PARAMS.GRIDDIMX);
}
