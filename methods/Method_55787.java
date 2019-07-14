public static int ncuMemcpy3DAsync(long pCopy,long hStream){
  long __functionAddress=Functions.Memcpy3DAsync;
  if (CHECKS) {
    CUDA_MEMCPY3D.validate(pCopy);
  }
  return callPPI(pCopy,hStream,__functionAddress);
}
