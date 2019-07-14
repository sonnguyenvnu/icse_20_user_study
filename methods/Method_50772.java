private void findSanitizedVariables(AbstractApexNode<?> node){
  final ASTVariableExpression left=node.getFirstChildOfType(ASTVariableExpression.class);
  final ASTLiteralExpression literal=node.getFirstChildOfType(ASTLiteralExpression.class);
  final ASTMethodCallExpression right=node.getFirstChildOfType(ASTMethodCallExpression.class);
  if (literal != null) {
    if (left != null) {
      if (literal.isInteger() || literal.isBoolean() || literal.isDouble()) {
        safeVariables.add(Helper.getFQVariableName(left));
      }
      if (literal.isString()) {
        if (SELECT_PATTERN.matcher(literal.getImage()).matches()) {
          selectContainingVariables.put(Helper.getFQVariableName(left),Boolean.TRUE);
        }
 else {
          safeVariables.add(Helper.getFQVariableName(left));
        }
      }
    }
  }
  if (right != null) {
    if (Helper.isMethodName(right,STRING,ESCAPE_SINGLE_QUOTES)) {
      if (left != null) {
        safeVariables.add(Helper.getFQVariableName(left));
      }
    }
  }
  if (node instanceof ASTVariableDeclaration) {
switch (((ASTVariableDeclaration)node).getType().toLowerCase(Locale.ROOT)) {
case INTEGER:
case ID:
case BOOLEAN:
case DECIMAL:
case LONG:
case DOUBLE:
      safeVariables.add(Helper.getFQVariableName(left));
    break;
default :
  break;
}
}
}
