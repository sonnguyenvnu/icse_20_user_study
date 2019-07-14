/** 
 * Unsafe version of  {@link #srcXInBytes(long) srcXInBytes}. 
 */
public static void nsrcXInBytes(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.SRCXINBYTES,value);
}
