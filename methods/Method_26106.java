private Optional<SuggestedFix> getFix(ExpressionTree patternArg,VisitorState state){
  if (patternArg.getKind() == Kind.STRING_LITERAL) {
    String replacement=state.getSourceForNode(patternArg).replace('Y','y');
    return Optional.of(SuggestedFix.replace(patternArg,replacement));
  }
  Symbol sym=ASTHelpers.getSymbol(patternArg);
  if (sym instanceof Symbol.VarSymbol && sym.getKind() == ElementKind.FIELD) {
    SuggestedFix[] fix={null};
    new TreeScanner<Void,Void>(){
      @Override public Void visitVariable(      VariableTree node,      Void aVoid){
        if (sym.equals(ASTHelpers.getSymbol(node)) && node.getInitializer() != null && node.getInitializer().getKind() == Kind.STRING_LITERAL) {
          String source=state.getSourceForNode(node.getInitializer());
          String replacement=source.replace('Y','y');
          if (!source.equals(replacement)) {
            fix[0]=SuggestedFix.replace(node.getInitializer(),replacement);
          }
        }
        return super.visitVariable(node,aVoid);
      }
    }
.scan(state.getPath().getCompilationUnit(),null);
    return Optional.ofNullable(fix[0]);
  }
  return Optional.empty();
}
