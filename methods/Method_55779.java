public static int ncuMemcpy2D(long pCopy){
  long __functionAddress=Functions.Memcpy2D;
  if (CHECKS) {
    CUDA_MEMCPY2D.validate(pCopy);
  }
  return callPI(pCopy,__functionAddress);
}
