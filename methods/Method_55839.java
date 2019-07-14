public static int ncuGraphAddMemcpyNode(long phGraphNode,long hGraph,long dependencies,long numDependencies,long copyParams,long ctx){
  long __functionAddress=Functions.GraphAddMemcpyNode;
  if (CHECKS) {
    check(hGraph);
    CUDA_MEMCPY3D.validate(copyParams);
    check(ctx);
  }
  return callPPPPPPI(phGraphNode,hGraph,dependencies,numDependencies,copyParams,ctx,__functionAddress);
}
