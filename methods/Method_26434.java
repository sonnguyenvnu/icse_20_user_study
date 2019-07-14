private static boolean treeToStringInBugChecker(Type type,VisitorState state){
  ClassTree enclosingClass=ASTHelpers.findEnclosingNode(state.getPath(),ClassTree.class);
  if (enclosingClass == null || !IS_BUGCHECKER.matches(enclosingClass,state)) {
    return false;
  }
  return IS_TREE.apply(type,state);
}
