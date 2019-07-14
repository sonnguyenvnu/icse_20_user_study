@NativeType("CUresult") public static int cuGraphGetEdges(@NativeType("CUgraph") long hGraph,@Nullable @NativeType("CUgraphNode *") PointerBuffer from,@Nullable @NativeType("CUgraphNode *") PointerBuffer to,@NativeType("size_t *") PointerBuffer numEdges){
  if (CHECKS) {
    check(numEdges,1);
    checkSafe(from,numEdges.get(numEdges.position()));
    checkSafe(to,numEdges.get(numEdges.position()));
  }
  return ncuGraphGetEdges(hGraph,memAddressSafe(from),memAddressSafe(to),memAddress(numEdges));
}
