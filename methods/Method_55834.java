public static int ncuSignalExternalSemaphoresAsync(long extSemArray,long paramsArray,int numExtSems,long stream){
  long __functionAddress=Functions.SignalExternalSemaphoresAsync;
  return callPPPI(extSemArray,paramsArray,numExtSems,stream,__functionAddress);
}
