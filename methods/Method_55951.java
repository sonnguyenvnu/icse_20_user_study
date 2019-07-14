/** 
 * Unsafe version of  {@link #srcLOD(long) srcLOD}. 
 */
public static void nsrcLOD(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.SRCLOD,value);
}
