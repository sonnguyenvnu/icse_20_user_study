public static int ncuMemcpyDtoHAsync(long dstHost,long srcDevice,long ByteCount,long hStream){
  long __functionAddress=Functions.MemcpyDtoHAsync;
  if (CHECKS) {
    check(srcDevice);
  }
  return callPPPPI(dstHost,srcDevice,ByteCount,hStream,__functionAddress);
}
