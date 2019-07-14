public static int ncuMemcpy3D(long pCopy){
  long __functionAddress=Functions.Memcpy3D;
  if (CHECKS) {
    CUDA_MEMCPY3D.validate(pCopy);
  }
  return callPI(pCopy,__functionAddress);
}
