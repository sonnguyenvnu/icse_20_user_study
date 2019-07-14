private static ImmutableList<SuggestedFix> buildUnusedParameterFixes(Symbol varSymbol,List<TreePath> usagePaths,VisitorState state){
  MethodSymbol methodSymbol=(MethodSymbol)varSymbol.owner;
  int index=methodSymbol.params.indexOf(varSymbol);
  SuggestedFix.Builder fix=SuggestedFix.builder();
  for (  TreePath path : usagePaths) {
    fix.delete(path.getLeaf());
  }
  new TreePathScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree tree,    Void unused){
      if (getSymbol(tree).equals(methodSymbol)) {
        removeByIndex(tree.getArguments());
      }
      return super.visitMethodInvocation(tree,null);
    }
    @Override public Void visitMethod(    MethodTree tree,    Void unused){
      if (getSymbol(tree).equals(methodSymbol)) {
        removeByIndex(tree.getParameters());
      }
      return super.visitMethod(tree,null);
    }
    private void removeByIndex(    List<? extends Tree> trees){
      if (index >= trees.size()) {
        return;
      }
      if (trees.size() == 1) {
        Tree tree=getOnlyElement(trees);
        if (((JCTree)tree).getStartPosition() == -1 || state.getEndPosition(tree) == -1) {
          return;
        }
        fix.delete(tree);
        return;
      }
      int startPos;
      int endPos;
      if (index >= 1) {
        startPos=state.getEndPosition(trees.get(index - 1));
        endPos=state.getEndPosition(trees.get(index));
      }
 else {
        startPos=((JCTree)trees.get(index)).getStartPosition();
        endPos=((JCTree)trees.get(index + 1)).getStartPosition();
      }
      if (index == methodSymbol.params().size() - 1 && methodSymbol.isVarArgs()) {
        endPos=state.getEndPosition(getLast(trees));
      }
      if (startPos == Position.NOPOS || endPos == Position.NOPOS) {
        return;
      }
      fix.replace(startPos,endPos,"");
    }
  }
.scan(state.getPath().getCompilationUnit(),null);
  return ImmutableList.of(fix.build());
}
