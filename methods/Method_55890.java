@NativeType("CUresult") public static int cuStreamWaitValue64(@NativeType("CUstream") long stream,@NativeType("CUdeviceptr") long addr,@NativeType("cuuint64_t") long value,@NativeType("unsigned int") int flags){
  long __functionAddress=Functions.StreamWaitValue64;
  if (CHECKS) {
    check(addr);
  }
  return callPPJI(stream,addr,value,flags,__functionAddress);
}
