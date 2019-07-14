/** 
 * Unsafe version of  {@link #userData}. 
 */
public static long nuserData(long struct){
  return memGetAddress(struct + CUDA_HOST_NODE_PARAMS.USERDATA);
}
