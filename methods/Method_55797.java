@NativeType("CUresult") public static int cuMemsetD2D16Async(@NativeType("CUdeviceptr") long dstDevice,@NativeType("size_t") long dstPitch,@NativeType("unsigned short") short us,@NativeType("size_t") long Width,@NativeType("size_t") long Height,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.MemsetD2D16Async;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPPPI(dstDevice,dstPitch,us,Width,Height,hStream,__functionAddress);
}
