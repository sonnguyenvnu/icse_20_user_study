/** 
 * Unsafe version of  {@link #srcZ(long) srcZ}. 
 */
public static void nsrcZ(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.SRCZ,value);
}
