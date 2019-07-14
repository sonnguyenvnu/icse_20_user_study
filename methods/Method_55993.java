/** 
 * Unsafe version of  {@link #width(long) width}. 
 */
public static void nwidth(long struct,long value){
  memPutAddress(struct + CUDA_RESOURCE_VIEW_DESC.WIDTH,value);
}
