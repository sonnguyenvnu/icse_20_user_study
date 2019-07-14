/** 
 * Unsafe version of  {@link #res_linear_numChannels(int) res_linear_numChannels}. 
 */
public static void nres_linear_numChannels(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_RESOURCE_DESC.RES_LINEAR_NUMCHANNELS,value);
}
