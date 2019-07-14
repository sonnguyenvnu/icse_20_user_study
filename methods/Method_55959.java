/** 
 * Unsafe version of  {@link #dstZ(long) dstZ}. 
 */
public static void ndstZ(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTZ,value);
}
