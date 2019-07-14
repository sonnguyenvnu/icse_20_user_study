/** 
 * Unsafe version of  {@link #res_pitch2D_numChannels(int) res_pitch2D_numChannels}. 
 */
public static void nres_pitch2D_numChannels(long struct,int value){
  UNSAFE.putInt(null,struct + CUDA_RESOURCE_DESC.RES_PITCH2D_NUMCHANNELS,value);
}
