@Override public Description matchVariable(VariableTree tree,VisitorState state){
  Symbol symbol=getSymbol(tree);
  if (symbol.getKind() != ElementKind.FIELD) {
    return NO_MATCH;
  }
  return handle(tree,tree.getName(),tree.getModifiers(),state);
}
