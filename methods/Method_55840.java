public static int ncuGraphMemcpyNodeSetParams(long hNode,long nodeParams){
  long __functionAddress=Functions.GraphMemcpyNodeSetParams;
  if (CHECKS) {
    check(hNode);
    CUDA_MEMCPY3D.validate(nodeParams);
  }
  return callPPI(hNode,nodeParams,__functionAddress);
}
