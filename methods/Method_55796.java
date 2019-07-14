@NativeType("CUresult") public static int cuMemsetD2D8Async(@NativeType("CUdeviceptr") long dstDevice,@NativeType("size_t") long dstPitch,@NativeType("unsigned char") byte uc,@NativeType("size_t") long Width,@NativeType("size_t") long Height,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.MemsetD2D8Async;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPPPI(dstDevice,dstPitch,uc,Width,Height,hStream,__functionAddress);
}
