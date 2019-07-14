@Override public Description matchMethod(MethodTree methodTree,VisitorState state){
  DocTreePath path=Utils.getDocTreePath(state);
  if (path != null) {
    new VoidReturnTypeChecker(state,methodTree).scan(path,null);
  }
  return Description.NO_MATCH;
}
