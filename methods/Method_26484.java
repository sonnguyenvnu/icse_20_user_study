@Override public Description matchVariable(VariableTree tree,VisitorState state){
  if (tree.getInitializer() == null) {
    return NO_MATCH;
  }
  LambdaExpressionTree lambda=LAMBDA_VISITOR.visit(tree.getInitializer(),null);
  if (lambda == null) {
    return NO_MATCH;
  }
  Symbol sym=getSymbol(tree);
  if (sym == null || sym.getKind() != ElementKind.FIELD || !sym.isPrivate() || !sym.getModifiers().contains(Modifier.FINAL)) {
    return NO_MATCH;
  }
  if (ASTHelpers.hasAnnotation(tree,"com.google.inject.testing.fieldbinder.Bind",state)) {
    return NO_MATCH;
  }
  Tree type=tree.getType();
  if (!canFix(type,sym,state)) {
    return NO_MATCH;
  }
  if (state.isAndroidCompatible()) {
    return NO_MATCH;
  }
  SuggestedFix.Builder fix=SuggestedFix.builder();
  String name=sym.isStatic() ? UPPER_UNDERSCORE.converterTo(LOWER_CAMEL).convert(tree.getName().toString()) : tree.getName().toString();
  new TreePathScanner<Void,Void>(){
    @Override public Void visitMemberSelect(    MemberSelectTree node,    Void unused){
      if (Objects.equals(getSymbol(node),sym)) {
        replaceUseWithMethodReference(fix,node,name,state.withPath(getCurrentPath()));
      }
      return super.visitMemberSelect(node,null);
    }
    @Override public Void visitIdentifier(    IdentifierTree node,    Void unused){
      if (Objects.equals(getSymbol(node),sym)) {
        replaceUseWithMethodReference(fix,node,name,state.withPath(getCurrentPath()));
      }
      return super.visitIdentifier(node,null);
    }
  }
.scan(state.getPath().getCompilationUnit(),null);
  SuggestedFixes.removeModifiers(tree,state,Modifier.FINAL).ifPresent(fix::merge);
  lambdaToMethod(state,lambda,fix,name,type);
  return describeMatch(tree,fix.build());
}
