/** 
 * Unsafe version of  {@link #srcPitch(long) srcPitch}. 
 */
public static void nsrcPitch(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.SRCPITCH,value);
}
