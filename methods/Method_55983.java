/** 
 * Unsafe version of  {@link #res_pitch2D_width(long) res_pitch2D_width}. 
 */
public static void nres_pitch2D_width(long struct,long value){
  memPutAddress(struct + CUDA_RESOURCE_DESC.RES_PITCH2D_WIDTH,value);
}
