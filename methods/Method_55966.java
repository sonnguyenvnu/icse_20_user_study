/** 
 * Unsafe version of  {@link #dstHeight(long) dstHeight}. 
 */
public static void ndstHeight(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTHEIGHT,value);
}
