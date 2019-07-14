/** 
 * Unsafe version of  {@link #dstY}. 
 */
public static long ndstY(long struct){
  return memGetAddress(struct + CUDA_MEMCPY3D_PEER.DSTY);
}
