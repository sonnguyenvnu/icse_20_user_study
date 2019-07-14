/** 
 * If the left operand of an int binary expression is an int literal, suggest making it a long.
 */
private Fix longFix(ExpressionTree expr,VisitorState state){
  BinaryTree binExpr=null;
  while (expr instanceof BinaryTree) {
    binExpr=(BinaryTree)expr;
    expr=binExpr.getLeftOperand();
  }
  if (!(expr instanceof LiteralTree) || expr.getKind() != Kind.INT_LITERAL) {
    return null;
  }
  Type intType=state.getSymtab().intType;
  if (!isSameType(getType(binExpr),intType,state)) {
    return null;
  }
  SuggestedFix.Builder fix=SuggestedFix.builder().postfixWith(expr,"L");
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (parent instanceof VariableTree && isSameType(getType(parent),intType,state)) {
    fix.replace(((VariableTree)parent).getType(),"long");
  }
  return fix.build();
}
