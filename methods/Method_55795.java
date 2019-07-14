@NativeType("CUresult") public static int cuMemsetD32Async(@NativeType("CUdeviceptr") long dstDevice,@NativeType("unsigned int") int ui,@NativeType("size_t") long N,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.MemsetD32Async;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPI(dstDevice,ui,N,hStream,__functionAddress);
}
