private static boolean floatingPointArgument(ExpressionTree tree){
  if (tree.getKind() == Kind.UNARY_PLUS || tree.getKind() == Kind.UNARY_MINUS) {
    tree=((UnaryTree)tree).getExpression();
  }
  return tree.getKind() == Kind.DOUBLE_LITERAL || tree.getKind() == Kind.FLOAT_LITERAL;
}
