/** 
 * Unsafe version of  {@link #srcDevice(long) srcDevice}. 
 */
public static void nsrcDevice(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.SRCDEVICE,check(value));
}
