/** 
 * Unsafe version of  {@link #depth}. 
 */
public static long ndepth(long struct){
  return memGetAddress(struct + CUDA_RESOURCE_VIEW_DESC.DEPTH);
}
