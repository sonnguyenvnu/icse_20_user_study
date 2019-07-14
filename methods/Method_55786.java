public static int ncuMemcpy2DAsync(long pCopy,long hStream){
  long __functionAddress=Functions.Memcpy2DAsync;
  if (CHECKS) {
    CUDA_MEMCPY2D.validate(pCopy);
  }
  return callPPI(pCopy,hStream,__functionAddress);
}
