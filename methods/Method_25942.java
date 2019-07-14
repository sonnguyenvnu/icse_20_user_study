private Tree getCurrentlyAnnotatedNode(VisitorState state){
  return state.getPath().getParentPath().getParentPath().getLeaf();
}
