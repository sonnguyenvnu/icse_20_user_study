/** 
 * Unsafe version of  {@link #dstContext(long) dstContext}. 
 */
public static void ndstContext(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTCONTEXT,check(value));
}
