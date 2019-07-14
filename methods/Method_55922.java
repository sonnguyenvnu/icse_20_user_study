/** 
 * Unsafe version of  {@link #userData(long) userData}. 
 */
public static void nuserData(long struct,long value){
  memPutAddress(struct + CUDA_HOST_NODE_PARAMS.USERDATA,check(value));
}
