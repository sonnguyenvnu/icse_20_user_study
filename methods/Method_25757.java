@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  Symbol sym=ASTHelpers.getSymbol(tree.getIdentifier());
  if (sym == null) {
    return NO_MATCH;
  }
  Types types=state.getTypes();
  Symtab symtab=state.getSymtab();
  if (sym.equals(types.boxedClass(symtab.byteType)) || sym.equals(types.boxedClass(symtab.charType)) || sym.equals(types.boxedClass(symtab.shortType)) || sym.equals(types.boxedClass(symtab.intType)) || sym.equals(types.boxedClass(symtab.longType)) || sym.equals(types.boxedClass(symtab.doubleType)) || sym.equals(types.boxedClass(symtab.floatType)) || sym.equals(types.boxedClass(symtab.booleanType))) {
    return describeMatch(tree,buildFix(tree,state));
  }
  return NO_MATCH;
}
