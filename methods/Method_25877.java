private SuggestedFix fixAsserts(ClassTree tree,VisitorState state){
  SuggestedFix.Builder fix=SuggestedFix.builder();
  tree.accept(new TreeScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree tree,    Void unused){
      if (STATIC_ASSERT.matches(tree,state)) {
        String assertType=ASTHelpers.getSymbol(tree).getSimpleName().toString();
        fix.addStaticImport("org.junit.Assert." + assertType);
      }
      return super.visitMethodInvocation(tree,unused);
    }
  }
,null);
  Tree extendsClause=tree.getExtendsClause();
  int endOfExtendsClause=state.getEndPosition(extendsClause);
  List<ErrorProneToken> tokens=state.getOffsetTokensForNode(tree);
  int startPos=0;
  for (  ErrorProneToken token : tokens) {
    if (token.pos() > endOfExtendsClause) {
      break;
    }
    if (token.kind() == TokenKind.EXTENDS) {
      int curr=token.pos();
      if (curr > startPos) {
        startPos=curr;
      }
    }
  }
  return fix.replace(startPos,endOfExtendsClause,"").build();
}
