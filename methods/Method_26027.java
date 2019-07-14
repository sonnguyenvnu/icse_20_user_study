private boolean shouldSkip(VisitorState state,Type type){
  TreePath path=findEnclosingMethod(state);
  if (path == null) {
    return false;
  }
  MethodTree enclosingMethod=(MethodTree)path.getLeaf();
  if (enclosingMethod == null) {
    return false;
  }
  return implementingObsoleteMethod(enclosingMethod,state,type) || mockingObsoleteMethod(enclosingMethod,state,type);
}
