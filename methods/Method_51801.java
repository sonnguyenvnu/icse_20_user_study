public boolean isAnonymousInnerClass(){
  return jjtGetParent() instanceof ASTAllocationExpression;
}
