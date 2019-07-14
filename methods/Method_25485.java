/** 
 * Updates current suppression state with information for the given  {@code tree}. Returns the previous suppression state so that it can be restored when going up the tree.
 */
private SuppressionInfo updateSuppressions(Tree tree,VisitorState state){
  SuppressionInfo prevSuppressionInfo=currentSuppressions;
  if (tree instanceof CompilationUnitTree) {
    currentSuppressions=currentSuppressions.forCompilationUnit((CompilationUnitTree)tree,state);
  }
 else {
    Symbol sym=ASTHelpers.getDeclaredSymbol(tree);
    if (sym != null) {
      currentSuppressions=currentSuppressions.withExtendedSuppressions(sym,state,getCustomSuppressionAnnotations());
    }
  }
  return prevSuppressionInfo;
}
