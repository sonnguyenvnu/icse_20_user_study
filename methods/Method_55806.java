@NativeType("CUresult") public static int cuEventQuery(@NativeType("CUevent") long hEvent){
  long __functionAddress=Functions.EventQuery;
  if (CHECKS) {
    check(hEvent);
  }
  return callPI(hEvent,__functionAddress);
}
