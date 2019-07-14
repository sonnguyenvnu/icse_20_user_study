@Override public int getBeginColumn(){
  if (beginColumnDiff > -1) {
    return super.getBeginColumn() - beginColumnDiff;
  }
  if (jjtGetNumChildren() > 0 && jjtGetChild(0) instanceof ASTMethodCallExpression) {
    ASTMethodCallExpression methodCallExpression=(ASTMethodCallExpression)jjtGetChild(0);
    int fullLength=methodCallExpression.getFullMethodName().length();
    int nameLength=methodCallExpression.getMethodName().length();
    if (fullLength > nameLength) {
      beginColumnDiff=fullLength - nameLength;
    }
 else {
      beginColumnDiff=0;
    }
  }
  return super.getBeginColumn() - beginColumnDiff;
}
