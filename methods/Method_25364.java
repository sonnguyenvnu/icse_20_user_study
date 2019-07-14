@Override public boolean matches(Tree item,VisitorState state){
  Symbol sym=ASTHelpers.getSymbol(item);
  return symbolClass.isAssignableFrom(sym.getClass());
}
