@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!ASSERTION.matches(tree,state)) {
    return NO_MATCH;
  }
  JCTry tryStatement=enclosingTry(state);
  if (tryStatement == null) {
    return NO_MATCH;
  }
  Optional<JCCatch> maybeCatchTree=catchesType(tryStatement,state.getSymtab().assertionErrorType,state);
  if (!maybeCatchTree.isPresent()) {
    return NO_MATCH;
  }
  JCCatch catchTree=maybeCatchTree.get();
  VarSymbol parameter=ASTHelpers.getSymbol(catchTree.getParameter());
  boolean rethrows=firstNonNull(new TreeScanner<Boolean,Void>(){
    @Override public Boolean visitThrow(    ThrowTree tree,    Void unused){
      if (Objects.equals(parameter,ASTHelpers.getSymbol(tree.getExpression()))) {
        return true;
      }
      if (NEW_THROWABLE.matches(tree.getExpression(),state) && ((NewClassTree)tree.getExpression()).getArguments().stream().anyMatch(arg -> Objects.equals(parameter,ASTHelpers.getSymbol(arg)))) {
        return true;
      }
      return super.visitThrow(tree,null);
    }
    @Override public Boolean reduce(    Boolean a,    Boolean b){
      return firstNonNull(a,false) || firstNonNull(b,false);
    }
  }
.scan(catchTree.getBlock(),null),false);
  if (rethrows) {
    return NO_MATCH;
  }
  Description.Builder description=buildDescription(tree);
  buildFix(tryStatement,tree,state).ifPresent(description::addFix);
  return description.build();
}
