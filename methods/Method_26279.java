private ExpressionType isGreaterThanEqualToZero(BinaryTree tree){
  ExpressionTree literalOperand;
  ExpressionType returnType;
switch (tree.getKind()) {
case GREATER_THAN_EQUAL:
    literalOperand=tree.getRightOperand();
  returnType=ExpressionType.GREATER_THAN_EQUAL;
break;
case LESS_THAN_EQUAL:
literalOperand=tree.getLeftOperand();
returnType=ExpressionType.LESS_THAN_EQUAL;
break;
default :
return ExpressionType.MISMATCH;
}
if (literalOperand.getKind() != Kind.INT_LITERAL) {
return ExpressionType.MISMATCH;
}
if (!((LiteralTree)literalOperand).getValue().equals(0)) {
return ExpressionType.MISMATCH;
}
return returnType;
}
