public static int ncuGraphInstantiate(long phGraphExec,long hGraph,long phErrorNode,long logBuffer,long bufferSize){
  long __functionAddress=Functions.GraphInstantiate;
  if (CHECKS) {
    check(hGraph);
  }
  return callPPPPPI(phGraphExec,hGraph,phErrorNode,logBuffer,bufferSize,__functionAddress);
}
