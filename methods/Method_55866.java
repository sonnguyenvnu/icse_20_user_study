@NativeType("CUresult") public static int cuDeviceGetByPCIBusId(@NativeType("CUdevice *") IntBuffer dev,@NativeType("char const *") CharSequence pciBusId){
  if (CHECKS) {
    check(dev,1);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(pciBusId,true);
    long pciBusIdEncoded=stack.getPointerAddress();
    return ncuDeviceGetByPCIBusId(memAddress(dev),pciBusIdEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
