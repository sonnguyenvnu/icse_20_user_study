/** 
 * Unsafe version of  {@link #dstXInBytes(long) dstXInBytes}. 
 */
public static void ndstXInBytes(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTXINBYTES,value);
}
