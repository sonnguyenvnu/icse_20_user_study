/** 
 * Unsafe version of  {@link #dstY(long) dstY}. 
 */
public static void ndstY(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTY,value);
}
