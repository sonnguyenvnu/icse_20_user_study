/** 
 * Unsafe version of  {@link #width}. 
 */
public static long nwidth(long struct){
  return memGetAddress(struct + CUDA_RESOURCE_VIEW_DESC.WIDTH);
}
