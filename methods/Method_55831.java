@NativeType("CUresult") public static int cuDestroyExternalMemory(@NativeType("CUexternalMemory") long extMem){
  long __functionAddress=Functions.DestroyExternalMemory;
  if (CHECKS) {
    check(extMem);
  }
  return callPI(extMem,__functionAddress);
}
