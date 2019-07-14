@NativeType("CUresult") public static int cuEventSynchronize(@NativeType("CUevent") long hEvent){
  long __functionAddress=Functions.EventSynchronize;
  if (CHECKS) {
    check(hEvent);
  }
  return callPI(hEvent,__functionAddress);
}
