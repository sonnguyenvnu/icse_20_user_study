@NativeType("CUresult") public static int cuMemPrefetchAsync(@NativeType("CUdeviceptr") long devPtr,@NativeType("size_t") long count,@NativeType("CUdevice") int dstDevice,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.MemPrefetchAsync;
  if (CHECKS) {
    check(devPtr);
  }
  return callPPPI(devPtr,count,dstDevice,hStream,__functionAddress);
}
