private boolean isAllocatedStringBuffer(ASTAdditiveExpression node){
  ASTAllocationExpression ao=node.getFirstParentOfType(ASTAllocationExpression.class);
  if (ao == null) {
    return false;
  }
  ASTClassOrInterfaceType an=ao.getFirstChildOfType(ASTClassOrInterfaceType.class);
  return an != null && TypeHelper.isEither(an,StringBuffer.class,StringBuilder.class);
}
