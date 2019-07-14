Description check(Type targetType,ExpressionTree init){
  if (init == null || targetType == null) {
    return NO_MATCH;
  }
  if (ASTHelpers.constValue(init) != null) {
    return NO_MATCH;
  }
  if (targetType.getKind() != TypeKind.LONG) {
    return NO_MATCH;
  }
  if (ASTHelpers.getType(init).getKind() != TypeKind.INT) {
    return NO_MATCH;
  }
  BinaryTree innerMost=null;
  ExpressionTree nested=init;
  while (true) {
    nested=ASTHelpers.stripParentheses(nested);
    if (!(nested instanceof BinaryTree)) {
      break;
    }
switch (nested.getKind()) {
case DIVIDE:
case REMAINDER:
case AND:
case XOR:
case OR:
case RIGHT_SHIFT:
      break;
default :
    innerMost=(BinaryTree)nested;
}
nested=((BinaryTree)nested).getLeftOperand();
}
if (innerMost == null) {
return NO_MATCH;
}
if (innerMost.getLeftOperand().getKind() == Kind.INT_LITERAL) {
return describeMatch(init,SuggestedFix.postfixWith(innerMost.getLeftOperand(),"L"));
}
if (innerMost.getRightOperand().getKind() == Kind.INT_LITERAL) {
return describeMatch(init,SuggestedFix.postfixWith(innerMost.getRightOperand(),"L"));
}
return describeMatch(init,SuggestedFix.prefixWith(innerMost.getLeftOperand(),"(long) "));
}
