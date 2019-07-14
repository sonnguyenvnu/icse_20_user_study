public static int ncuMemcpyDtoH(long dstHost,long srcDevice,long ByteCount){
  long __functionAddress=Functions.MemcpyDtoH;
  if (CHECKS) {
    check(srcDevice);
  }
  return callPPPI(dstHost,srcDevice,ByteCount,__functionAddress);
}
