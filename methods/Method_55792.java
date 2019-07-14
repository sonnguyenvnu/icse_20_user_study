@NativeType("CUresult") public static int cuMemsetD2D32(@NativeType("CUdeviceptr") long dstDevice,@NativeType("size_t") long dstPitch,@NativeType("unsigned int") int ui,@NativeType("size_t") long Width,@NativeType("size_t") long Height){
  long __functionAddress=Functions.MemsetD2D32;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPPI(dstDevice,dstPitch,ui,Width,Height,__functionAddress);
}
