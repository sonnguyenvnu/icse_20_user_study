public static int ncuMemcpy3DPeerAsync(long pCopy,long hStream){
  long __functionAddress=Functions.Memcpy3DPeerAsync;
  if (CHECKS) {
    CUDA_MEMCPY3D_PEER.validate(pCopy);
  }
  return callPPI(pCopy,hStream,__functionAddress);
}
