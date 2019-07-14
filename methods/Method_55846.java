@NativeType("CUresult") public static int cuGraphNodeFindInClone(@NativeType("CUgraphNode *") PointerBuffer phNode,@NativeType("CUgraphNode") long hOriginalNode,@NativeType("CUgraph") long hClonedGraph){
  if (CHECKS) {
    check(phNode,1);
  }
  return ncuGraphNodeFindInClone(memAddress(phNode),hOriginalNode,hClonedGraph);
}
