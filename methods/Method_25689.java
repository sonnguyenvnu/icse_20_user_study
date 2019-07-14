private static boolean changeMustBeBetterThanOriginal(Changes changes,Tree node,MethodSymbol sym,VisitorState state){
  return changes.totalAssignmentCost() < changes.totalOriginalCost();
}
