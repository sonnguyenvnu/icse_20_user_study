@NativeType("CUresult") public static int cuMemFree(@NativeType("CUdeviceptr") long dptr){
  long __functionAddress=Functions.MemFree;
  if (CHECKS) {
    check(dptr);
  }
  return callPI(dptr,__functionAddress);
}
