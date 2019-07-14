@NativeType("CUresult") public static int cuStreamWaitValue32(@NativeType("CUstream") long stream,@NativeType("CUdeviceptr") long addr,@NativeType("cuuint32_t") int value,@NativeType("unsigned int") int flags){
  long __functionAddress=Functions.StreamWaitValue32;
  if (CHECKS) {
    check(addr);
  }
  return callPPI(stream,addr,value,flags,__functionAddress);
}
