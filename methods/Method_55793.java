@NativeType("CUresult") public static int cuMemsetD8Async(@NativeType("CUdeviceptr") long dstDevice,@NativeType("unsigned char") byte uc,@NativeType("size_t") long N,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.MemsetD8Async;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPI(dstDevice,uc,N,hStream,__functionAddress);
}
