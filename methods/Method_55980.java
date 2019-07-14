/** 
 * Unsafe version of  {@link #res_pitch2D_devPtr(long) res_pitch2D_devPtr}. 
 */
public static void nres_pitch2D_devPtr(long struct,long value){
  memPutAddress(struct + CUDA_RESOURCE_DESC.RES_PITCH2D_DEVPTR,check(value));
}
