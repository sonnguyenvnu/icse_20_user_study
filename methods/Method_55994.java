/** 
 * Unsafe version of  {@link #depth(long) depth}. 
 */
public static void ndepth(long struct,long value){
  memPutAddress(struct + CUDA_RESOURCE_VIEW_DESC.DEPTH,value);
}
