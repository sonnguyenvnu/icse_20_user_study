@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  Symbol.MethodSymbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null) {
    return Description.NO_MATCH;
  }
  return matchArguments(state,sym,tree.getArguments().iterator());
}
