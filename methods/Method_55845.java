@NativeType("CUresult") public static int cuGraphHostNodeSetParams(@NativeType("CUgraphNode") long hNode,@NativeType("CUDA_HOST_NODE_PARAMS const *") CUDA_HOST_NODE_PARAMS nodeParams){
  return ncuGraphHostNodeSetParams(hNode,nodeParams.address());
}
