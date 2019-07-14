/** 
 * Matches if this is a narrowing integral cast between signed types where the expression is a subtract.
 */
private boolean matches(TypeCastTree tree,VisitorState state){
  Type treeType=ASTHelpers.getType(tree.getType());
  if (treeType.getTag() != TypeTag.INT) {
    return false;
  }
  ExpressionTree expression=ASTHelpers.stripParentheses(tree.getExpression());
  if (expression.getKind() != Kind.MINUS) {
    return false;
  }
  Type expressionType=getTypeOfSubtract((BinaryTree)expression,state);
  TypeTag expressionTypeTag=state.getTypes().unboxedTypeOrType(expressionType).getTag();
  return (expressionTypeTag == TypeTag.LONG);
}
