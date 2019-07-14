public static int ncuMemRangeGetAttribute(long data,long dataSize,int attribute,long devPtr,long count){
  long __functionAddress=Functions.MemRangeGetAttribute;
  if (CHECKS) {
    check(devPtr);
  }
  return callPPPPI(data,dataSize,attribute,devPtr,count,__functionAddress);
}
