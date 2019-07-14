/** 
 * Unsafe version of  {@link #dstLOD(long) dstLOD}. 
 */
public static void ndstLOD(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTLOD,value);
}
