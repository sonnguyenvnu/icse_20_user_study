/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  check(memGetAddress(struct + CUDA_MEMCPY3D_PEER.SRCHOST));
  check(memGetAddress(struct + CUDA_MEMCPY3D_PEER.SRCDEVICE));
  check(memGetAddress(struct + CUDA_MEMCPY3D_PEER.SRCARRAY));
  check(memGetAddress(struct + CUDA_MEMCPY3D_PEER.SRCCONTEXT));
  check(memGetAddress(struct + CUDA_MEMCPY3D_PEER.DSTHOST));
  check(memGetAddress(struct + CUDA_MEMCPY3D_PEER.DSTDEVICE));
  check(memGetAddress(struct + CUDA_MEMCPY3D_PEER.DSTARRAY));
  check(memGetAddress(struct + CUDA_MEMCPY3D_PEER.DSTCONTEXT));
}
