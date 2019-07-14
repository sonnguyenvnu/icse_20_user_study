@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (!Matchers.toStringMethodDeclaration().matches(tree,state)) {
    return Description.NO_MATCH;
  }
  boolean hasReturnNull=tree.accept(new TreeScanner<Boolean,Void>(){
    @Override public Boolean visitLambdaExpression(    LambdaExpressionTree node,    Void unused){
      return false;
    }
    @Override public Boolean visitClass(    ClassTree node,    Void unused){
      return false;
    }
    @Override public Boolean visitReturn(    ReturnTree node,    Void unused){
      return node.getExpression().getKind() == NULL_LITERAL;
    }
    @Override public Boolean reduce(    Boolean r1,    Boolean r2){
      return (r1 != null && r1) || (r2 != null && r2);
    }
  }
,null);
  return hasReturnNull ? describeMatch(tree) : Description.NO_MATCH;
}
