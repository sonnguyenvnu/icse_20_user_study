public static int ncuMemRangeGetAttributes(long data,long dataSizes,long attributes,long numAttributes,long devPtr,long count){
  long __functionAddress=Functions.MemRangeGetAttributes;
  if (CHECKS) {
    check(devPtr);
  }
  return callPPPPPPI(data,dataSizes,attributes,numAttributes,devPtr,count,__functionAddress);
}
