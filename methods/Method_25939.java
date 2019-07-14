@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (tree.getBody() == null || tree.getBody().getStatements().size() != 1) {
    return NO_MATCH;
  }
  Tree statement=stripParentheses(getOnlyElement(tree.getBody().getStatements()));
  ExpressionTree expr=statement.accept(new SimpleTreeVisitor<ExpressionTree,Void>(){
    @Override public ExpressionTree visitExpressionStatement(    ExpressionStatementTree tree,    Void unused){
      return tree.getExpression();
    }
    @Override public ExpressionTree visitReturn(    ReturnTree tree,    Void unused){
      return tree.getExpression();
    }
  }
,null);
  if (!(expr instanceof MethodInvocationTree)) {
    return NO_MATCH;
  }
  ExpressionTree select=((MethodInvocationTree)expr).getMethodSelect();
  MethodSymbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null || !sym.equals(ASTHelpers.getSymbol(expr))) {
    return NO_MATCH;
  }
  if (!sym.isStatic()) {
switch (select.getKind()) {
case IDENTIFIER:
      break;
case MEMBER_SELECT:
    ExpressionTree receiver=((MemberSelectTree)select).getExpression();
  if (receiver.getKind() != Kind.IDENTIFIER) {
    return NO_MATCH;
  }
if (!((IdentifierTree)receiver).getName().contentEquals("this")) {
  return NO_MATCH;
}
break;
default :
return NO_MATCH;
}
}
return describeMatch(statement);
}
