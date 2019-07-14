public static int ncuGraphNodeGetDependentNodes(long hNode,long dependentNodes,long numDependentNodes){
  long __functionAddress=Functions.GraphNodeGetDependentNodes;
  if (CHECKS) {
    check(hNode);
  }
  return callPPPI(hNode,dependentNodes,numDependentNodes,__functionAddress);
}
