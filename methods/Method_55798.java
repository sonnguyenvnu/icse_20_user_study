@NativeType("CUresult") public static int cuMemsetD2D32Async(@NativeType("CUdeviceptr") long dstDevice,@NativeType("size_t") long dstPitch,@NativeType("unsigned int") int ui,@NativeType("size_t") long Width,@NativeType("size_t") long Height,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.MemsetD2D32Async;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPPPI(dstDevice,dstPitch,ui,Width,Height,hStream,__functionAddress);
}
