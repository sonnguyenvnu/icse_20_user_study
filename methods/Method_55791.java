@NativeType("CUresult") public static int cuMemsetD2D8(@NativeType("CUdeviceptr") long dstDevice,@NativeType("size_t") long dstPitch,@NativeType("unsigned char") byte uc,@NativeType("size_t") long Width,@NativeType("size_t") long Height){
  long __functionAddress=Functions.MemsetD2D8;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPPI(dstDevice,dstPitch,uc,Width,Height,__functionAddress);
}
