@NativeType("CUresult") public static int cuStreamWaitEvent(@NativeType("CUstream") long hStream,@NativeType("CUevent") long hEvent,@NativeType("unsigned int") int Flags){
  long __functionAddress=Functions.StreamWaitEvent;
  if (CHECKS) {
    check(hEvent);
  }
  return callPPI(hStream,hEvent,Flags,__functionAddress);
}
