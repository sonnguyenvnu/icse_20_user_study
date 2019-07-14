/** 
 * Unsafe version of  {@link #srcHeight(long) srcHeight}. 
 */
public static void nsrcHeight(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.SRCHEIGHT,value);
}
