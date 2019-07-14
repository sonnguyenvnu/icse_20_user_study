public static int ncuMemcpyHtoAAsync(long dstArray,long dstOffset,long srcHost,long ByteCount,long hStream){
  long __functionAddress=Functions.MemcpyHtoAAsync;
  if (CHECKS) {
    check(dstArray);
  }
  return callPPPPPI(dstArray,dstOffset,srcHost,ByteCount,hStream,__functionAddress);
}
