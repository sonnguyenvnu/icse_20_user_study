/** 
 * Unsafe version of  {@link #srcMemoryType}. 
 */
public static int nsrcMemoryType(long struct){
  return UNSAFE.getInt(null,struct + CUDA_MEMCPY3D_PEER.SRCMEMORYTYPE);
}
