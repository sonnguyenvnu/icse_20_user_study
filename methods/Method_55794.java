@NativeType("CUresult") public static int cuMemsetD16Async(@NativeType("CUdeviceptr") long dstDevice,@NativeType("unsigned short") short us,@NativeType("size_t") long N,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.MemsetD16Async;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPI(dstDevice,us,N,hStream,__functionAddress);
}
