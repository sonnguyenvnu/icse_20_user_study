/** 
 * Unsafe version of  {@link #res_pitch2D_pitchInBytes(long) res_pitch2D_pitchInBytes}. 
 */
public static void nres_pitch2D_pitchInBytes(long struct,long value){
  memPutAddress(struct + CUDA_RESOURCE_DESC.RES_PITCH2D_PITCHINBYTES,value);
}
