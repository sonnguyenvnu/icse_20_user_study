public static int ncuMemcpyAtoHAsync(long dstHost,long srcArray,long srcOffset,long ByteCount,long hStream){
  long __functionAddress=Functions.MemcpyAtoHAsync;
  if (CHECKS) {
    check(srcArray);
  }
  return callPPPPPI(dstHost,srcArray,srcOffset,ByteCount,hStream,__functionAddress);
}
