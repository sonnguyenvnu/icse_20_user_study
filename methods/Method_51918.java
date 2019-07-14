private boolean isLambdaTypeInferred(){
  return getNthParent(3) instanceof ASTLambdaExpression && jjtGetParent().getFirstChildOfType(ASTType.class) == null;
}
