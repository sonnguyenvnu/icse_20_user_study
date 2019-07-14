@Override public boolean apply(Type type,VisitorState state){
  if (type == null) {
    return false;
  }
  for (  Supplier<Type> supplier : types) {
    Type expected=supplier.get(state);
    if (expected == null) {
      continue;
    }
    if (ASTHelpers.isSameType(expected,type,state)) {
      return true;
    }
  }
  return false;
}
