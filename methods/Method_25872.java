@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (!equalsMethodDeclaration().matches(tree,state)) {
    return NO_MATCH;
  }
  Symbol parameter=getSymbol(getOnlyElement(tree.getParameters()));
  new TreePathScanner<Void,Void>(){
    @Override public Void visitInstanceOf(    InstanceOfTree node,    Void unused){
      checkedTypes.add(getType(node.getType()));
      return super.visitInstanceOf(node,null);
    }
    @Override public Void visitMethodInvocation(    MethodInvocationTree node,    Void unused){
      methodInvoked=true;
      return null;
    }
    @Override public Void visitTypeCast(    TypeCastTree node,    Void unused){
      ExpressionTree expression=node.getExpression();
      if (!methodInvoked && expression.getKind() == Kind.IDENTIFIER && parameter.equals(getSymbol(expression)) && checkedTypes.stream().noneMatch(t -> isSubtype(t,getType(node.getType()),state))) {
        StatementTree enclosingStatement=findEnclosingNode(getCurrentPath(),StatementTree.class);
        state.reportMatch(describeMatch(node,SuggestedFix.prefixWith(enclosingStatement,String.format(INSTANCEOF_CHECK,state.getSourceForNode(expression),state.getSourceForNode(node.getType())))));
      }
      return super.visitTypeCast(node,null);
    }
  }
.scan(state.getPath(),null);
  return NO_MATCH;
}
