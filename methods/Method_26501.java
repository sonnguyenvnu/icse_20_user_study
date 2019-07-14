@Override public Description matchCatch(CatchTree tree,VisitorState state){
  if (isSuppressed(tree.getParameter())) {
    return Description.NO_MATCH;
  }
  VarSymbol exceptionSymbol=ASTHelpers.getSymbol(tree.getParameter());
  AtomicBoolean symbolUsed=new AtomicBoolean(false);
  ((JCTree)tree).accept(new TreeScanner(){
    @Override public void visitIdent(    JCIdent identTree){
      if (exceptionSymbol.equals(identTree.sym)) {
        symbolUsed.set(true);
      }
    }
  }
);
  if (symbolUsed.get()) {
    return Description.NO_MATCH;
  }
  Set<JCThrow> throwTrees=new HashSet<>();
  ((JCTree)tree).accept(new TreeScanner(){
    @Override public void visitThrow(    JCThrow throwTree){
      super.visitThrow(throwTree);
      throwTrees.add(throwTree);
    }
    @Override public void visitTry(    JCTry tryTree){
    }
  }
);
  if (throwTrees.isEmpty()) {
    return Description.NO_MATCH;
  }
  SuggestedFix.Builder allFixes=SuggestedFix.builder();
  throwTrees.stream().filter(badThrow -> badThrow.getExpression() instanceof NewClassTree).forEach(badThrow -> fixConstructor((NewClassTree)badThrow.getExpression(),exceptionSymbol,state).ifPresent(allFixes::merge));
  return describeMatch(tree,allFixes.build());
}
