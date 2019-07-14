@Override public boolean apply(Type type,VisitorState state){
  Type expected=supplier.get(state);
  if (expected == null || type == null) {
    return false;
  }
  return ASTHelpers.isSameType(expected,type,state);
}
