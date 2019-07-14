public static int ncuMemcpyAtoH(long dstHost,long srcArray,long srcOffset,long ByteCount){
  long __functionAddress=Functions.MemcpyAtoH;
  if (CHECKS) {
    check(srcArray);
  }
  return callPPPPI(dstHost,srcArray,srcOffset,ByteCount,__functionAddress);
}
