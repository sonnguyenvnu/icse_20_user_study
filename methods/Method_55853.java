@NativeType("CUresult") public static int cuGraphLaunch(@NativeType("CUgraphExec") long hGraphExec,@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.GraphLaunch;
  if (CHECKS) {
    check(hGraphExec);
  }
  return callPPI(hGraphExec,hStream,__functionAddress);
}
