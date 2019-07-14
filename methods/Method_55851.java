@NativeType("CUresult") public static int cuGraphRemoveDependencies(@NativeType("CUgraph") long hGraph,@NativeType("CUgraphNode const *") PointerBuffer from,@NativeType("CUgraphNode const *") PointerBuffer to){
  if (CHECKS) {
    check(to,from.remaining());
  }
  return ncuGraphRemoveDependencies(hGraph,memAddress(from),memAddress(to),from.remaining());
}
