/** 
 * Unsafe version of  {@link #res_linear_devPtr(long) res_linear_devPtr}. 
 */
public static void nres_linear_devPtr(long struct,long value){
  memPutAddress(struct + CUDA_RESOURCE_DESC.RES_LINEAR_DEVPTR,check(value));
}
