@NativeType("CUresult") public static int cuEventRecord(@NativeType("CUevent") long hEvent,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.EventRecord;
  if (CHECKS) {
    check(hEvent);
  }
  return callPPI(hEvent,hStream,__functionAddress);
}
