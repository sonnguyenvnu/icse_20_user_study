public static int ncuGraphAddHostNode(long phGraphNode,long hGraph,long dependencies,long numDependencies,long nodeParams){
  long __functionAddress=Functions.GraphAddHostNode;
  if (CHECKS) {
    check(hGraph);
    CUDA_HOST_NODE_PARAMS.validate(nodeParams);
  }
  return callPPPPPI(phGraphNode,hGraph,dependencies,numDependencies,nodeParams,__functionAddress);
}
