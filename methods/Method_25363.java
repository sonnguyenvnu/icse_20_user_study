@Override public boolean matches(T tree,VisitorState state){
  return isSubtype(getType(tree),typeToCompareSupplier.get(state),state);
}
