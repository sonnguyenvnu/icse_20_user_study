@NativeType("CUresult") public static int cuGraphKernelNodeSetParams(@NativeType("CUgraphNode") long hNode,@NativeType("CUDA_KERNEL_NODE_PARAMS const *") CUDA_KERNEL_NODE_PARAMS nodeParams){
  return ncuGraphKernelNodeSetParams(hNode,nodeParams.address());
}
