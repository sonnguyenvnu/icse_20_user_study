public static int ncuStreamBatchMemOp(long stream,int count,long paramArray,int flags){
  long __functionAddress=Functions.StreamBatchMemOp;
  return callPPI(stream,count,paramArray,flags,__functionAddress);
}
