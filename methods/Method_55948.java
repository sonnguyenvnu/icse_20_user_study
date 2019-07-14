/** 
 * Unsafe version of  {@link #Height}. 
 */
public static long nHeight(long struct){
  return memGetAddress(struct + CUDA_MEMCPY3D_PEER.HEIGHT);
}
