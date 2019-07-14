@NativeType("CUresult") public static int cuMemsetD32(@NativeType("CUdeviceptr") long dstDevice,@NativeType("unsigned int") int ui,@NativeType("size_t") long N){
  long __functionAddress=Functions.MemsetD32;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPI(dstDevice,ui,N,__functionAddress);
}
