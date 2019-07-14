private static boolean allArgumentsMustMatch(Changes changes,Tree node,Symbol.MethodSymbol sym,VisitorState state){
  return changes.assignmentCost().stream().allMatch(c -> c < 1.0);
}
