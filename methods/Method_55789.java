@NativeType("CUresult") public static int cuMemsetD16(@NativeType("CUdeviceptr") long dstDevice,@NativeType("unsigned short") short us,@NativeType("size_t") long N){
  long __functionAddress=Functions.MemsetD16;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPI(dstDevice,us,N,__functionAddress);
}
