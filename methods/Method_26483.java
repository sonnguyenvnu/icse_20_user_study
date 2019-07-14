@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (!tree.getParameters().isEmpty() || !tree.getThrows().isEmpty()) {
    return NO_MATCH;
  }
  LambdaExpressionTree lambda=LAMBDA_VISITOR.visit(tree.getBody(),null);
  if (lambda == null) {
    return NO_MATCH;
  }
  Symbol sym=getSymbol(tree);
  if (sym == null || !sym.isPrivate()) {
    return NO_MATCH;
  }
  SuggestedFix.Builder fix=SuggestedFix.builder();
  String name=tree.getName().toString();
  Tree type=tree.getReturnType();
  if (!canFix(type,sym,state)) {
    return NO_MATCH;
  }
  if (state.isAndroidCompatible()) {
    return NO_MATCH;
  }
  new TreePathScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree node,    Void unused){
      if (Objects.equals(getSymbol(node),sym)) {
        replaceUseWithMethodReference(fix,node,name,state.withPath(getCurrentPath()));
      }
      return super.visitMethodInvocation(node,null);
    }
  }
.scan(state.getPath().getCompilationUnit(),null);
  lambdaToMethod(state,lambda,fix,name,type);
  return describeMatch(tree,fix.build());
}
