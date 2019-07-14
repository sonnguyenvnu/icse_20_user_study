public static int ncuMemcpyHtoDAsync(long dstDevice,long srcHost,long ByteCount,long hStream){
  long __functionAddress=Functions.MemcpyHtoDAsync;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPPI(dstDevice,srcHost,ByteCount,hStream,__functionAddress);
}
