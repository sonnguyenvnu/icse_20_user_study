@NativeType("CUresult") public static int cuGraphNodeGetDependencies(@NativeType("CUgraphNode") long hNode,@Nullable @NativeType("CUgraphNode *") PointerBuffer dependencies,@NativeType("size_t *") PointerBuffer numDependencies){
  if (CHECKS) {
    check(numDependencies,1);
    checkSafe(dependencies,numDependencies.get(numDependencies.position()));
  }
  return ncuGraphNodeGetDependencies(hNode,memAddressSafe(dependencies),memAddress(numDependencies));
}
