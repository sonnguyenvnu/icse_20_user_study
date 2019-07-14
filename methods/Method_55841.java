@NativeType("CUresult") public static int cuGraphMemcpyNodeSetParams(@NativeType("CUgraphNode") long hNode,@NativeType("CUDA_MEMCPY3D const *") CUDA_MEMCPY3D nodeParams){
  return ncuGraphMemcpyNodeSetParams(hNode,nodeParams.address());
}
