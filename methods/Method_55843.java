public static int ncuGraphMemsetNodeSetParams(long hNode,long nodeParams){
  long __functionAddress=Functions.GraphMemsetNodeSetParams;
  if (CHECKS) {
    check(hNode);
    CUDA_MEMSET_NODE_PARAMS.validate(nodeParams);
  }
  return callPPI(hNode,nodeParams,__functionAddress);
}
