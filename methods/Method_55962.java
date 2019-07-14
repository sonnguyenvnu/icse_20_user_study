/** 
 * Unsafe version of  {@link #dstDevice(long) dstDevice}. 
 */
public static void ndstDevice(long struct,long value){
  memPutAddress(struct + CUDA_MEMCPY3D_PEER.DSTDEVICE,check(value));
}
