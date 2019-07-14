/** 
 * Unsafe version of  {@link #gridDimY}. 
 */
public static int ngridDimY(long struct){
  return UNSAFE.getInt(null,struct + CUDA_LAUNCH_PARAMS.GRIDDIMY);
}
