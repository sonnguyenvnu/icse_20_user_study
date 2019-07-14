private boolean hasFieldAssignmentInCatch(TryTree tree,VisitorState state){
  return anyCatchBlockMatches(tree,state,FIELD_ASSIGNMENT_IN_BLOCK);
}
