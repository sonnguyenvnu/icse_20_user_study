@Override public boolean apply(Type type,VisitorState state){
  Type bound=expected.get(state);
  if (bound == null || type == null) {
    return false;
  }
  return ASTHelpers.isSubtype(type,bound,state);
}
