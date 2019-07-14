@Override public boolean matches(T tree,VisitorState state){
  Type typeToCompare=typeToCompareSupplier.get(state);
  return ASTHelpers.isSameType(ASTHelpers.getType(tree),typeToCompare,state);
}
