/** 
 * Unsafe version of  {@link #res_linear_numChannels}. 
 */
public static int nres_linear_numChannels(long struct){
  return UNSAFE.getInt(null,struct + CUDA_RESOURCE_DESC.RES_LINEAR_NUMCHANNELS);
}
