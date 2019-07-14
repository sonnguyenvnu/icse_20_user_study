@NativeType("CUresult") public static int cuMemAdvise(@NativeType("CUdeviceptr") long devPtr,@NativeType("size_t") long count,@NativeType("CUmem_advise") int advice,@NativeType("CUdevice") int device){
  long __functionAddress=Functions.MemAdvise;
  if (CHECKS) {
    check(devPtr);
  }
  return callPPI(devPtr,count,advice,device,__functionAddress);
}
