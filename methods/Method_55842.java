public static int ncuGraphAddMemsetNode(long phGraphNode,long hGraph,long dependencies,long numDependencies,long memsetParams,long ctx){
  long __functionAddress=Functions.GraphAddMemsetNode;
  if (CHECKS) {
    check(hGraph);
    CUDA_MEMSET_NODE_PARAMS.validate(memsetParams);
    check(ctx);
  }
  return callPPPPPPI(phGraphNode,hGraph,dependencies,numDependencies,memsetParams,ctx,__functionAddress);
}
