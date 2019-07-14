public boolean isAnonymousInnerClass(){
  return jjtGetParent().jjtGetParent() instanceof ASTAllocationExpression;
}
