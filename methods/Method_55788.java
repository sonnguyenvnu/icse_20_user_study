@NativeType("CUresult") public static int cuMemsetD8(@NativeType("CUdeviceptr") long dstDevice,@NativeType("unsigned char") byte uc,@NativeType("size_t") long N){
  long __functionAddress=Functions.MemsetD8;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPI(dstDevice,uc,N,__functionAddress);
}
