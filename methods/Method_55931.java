/** 
 * Unsafe version of  {@link #gridDimZ}. 
 */
public static int ngridDimZ(long struct){
  return UNSAFE.getInt(null,struct + CUDA_LAUNCH_PARAMS.GRIDDIMZ);
}
