@NativeType("CUresult") public static int cuDeviceGetByPCIBusId(@NativeType("CUdevice *") IntBuffer dev,@NativeType("char const *") ByteBuffer pciBusId){
  if (CHECKS) {
    check(dev,1);
    checkNT1(pciBusId);
  }
  return ncuDeviceGetByPCIBusId(memAddress(dev),memAddress(pciBusId));
}
