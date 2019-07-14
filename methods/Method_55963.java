/** 
 * Unsafe version of  {@link #dstArray(long) dstArray}. 
 */
public static void ndstArray(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTARRAY,check(value));
}
