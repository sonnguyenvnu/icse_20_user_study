@NativeType("CUresult") public static int cuStreamAttachMemAsync(@NativeType("CUstream") long hStream,@NativeType("CUdeviceptr") long dptr,@NativeType("size_t") long length,@NativeType("unsigned int") int flags){
  long __functionAddress=Functions.StreamAttachMemAsync;
  if (CHECKS) {
    check(dptr);
  }
  return callPPPI(hStream,dptr,length,flags,__functionAddress);
}
