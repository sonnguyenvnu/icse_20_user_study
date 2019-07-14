private static void addIfVariable(Tree tree,ImmutableSet.Builder<VarSymbol> setBuilder){
  if (tree.getKind() == Kind.VARIABLE) {
    setBuilder.add(ASTHelpers.getSymbol((VariableTree)tree));
  }
}
