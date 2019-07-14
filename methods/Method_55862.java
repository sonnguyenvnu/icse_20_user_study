public static int ncuMemcpy3DPeer(long pCopy){
  long __functionAddress=Functions.Memcpy3DPeer;
  if (CHECKS) {
    CUDA_MEMCPY3D_PEER.validate(pCopy);
  }
  return callPI(pCopy,__functionAddress);
}
