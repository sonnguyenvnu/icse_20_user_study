private void findInnerInsecureEndpoints(AbstractApexNode<?> node,ASTVariableExpression variableNode){
  ASTLiteralExpression literalNode=node.getFirstChildOfType(ASTLiteralExpression.class);
  if (literalNode != null && variableNode != null) {
    if (literalNode.isString()) {
      String literal=literalNode.getImage();
      if (PATTERN.matcher(literal).matches()) {
        httpEndpointStrings.add(Helper.getFQVariableName(variableNode));
      }
    }
  }
}
