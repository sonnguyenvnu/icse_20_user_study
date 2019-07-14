public static GuardedBySymbolResolver from(Tree tree,VisitorState visitorState){
  return GuardedBySymbolResolver.from(ASTHelpers.getSymbol(tree).owner.enclClass(),visitorState.getPath().getCompilationUnit(),visitorState.context,tree,visitorState);
}
