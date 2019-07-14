public static int ncuMemcpyHtoD(long dstDevice,long srcHost,long ByteCount){
  long __functionAddress=Functions.MemcpyHtoD;
  if (CHECKS) {
    check(dstDevice);
  }
  return callPPPI(dstDevice,srcHost,ByteCount,__functionAddress);
}
