/** 
 * Unsafe version of  {@link #WidthInBytes(long) WidthInBytes}. 
 */
public static void nWidthInBytes(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.WIDTHINBYTES,value);
}
