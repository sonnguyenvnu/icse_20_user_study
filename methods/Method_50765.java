/** 
 * Finds any variables being present in PageReference constructor
 * @param node - PageReference
 * @param data
 */
private void getObjectValue(ApexNode<?> node,Object data){
  final List<ASTVariableExpression> variableExpressions=node.findChildrenOfType(ASTVariableExpression.class);
  for (  ASTVariableExpression variable : variableExpressions) {
    if (variable.jjtGetChildIndex() == 0 && !listOfStringLiteralVariables.contains(Helper.getFQVariableName(variable))) {
      addViolation(data,variable);
    }
  }
  final List<ASTBinaryExpression> binaryExpressions=node.findChildrenOfType(ASTBinaryExpression.class);
  for (  ASTBinaryExpression z : binaryExpressions) {
    getObjectValue(z,data);
  }
}
