private void processVariableAssignments(AbstractApexNode<?> node,Object data,final boolean reverseOrder){
  ASTMethodCallExpression methodCallAssignment=node.getFirstChildOfType(ASTMethodCallExpression.class);
  if (methodCallAssignment != null) {
    String varType=null;
    if (node instanceof ASTVariableDeclaration) {
      varType=((ASTVariableDeclaration)node).getType();
    }
    if (varType == null || !"id".equalsIgnoreCase(varType)) {
      processInlineMethodCalls(methodCallAssignment,data,false);
    }
  }
  List<ASTVariableExpression> nodes=node.findChildrenOfType(ASTVariableExpression.class);
switch (nodes.size()) {
case 1:
{
      final List<ASTBinaryExpression> ops=node.findChildrenOfType(ASTBinaryExpression.class);
      if (!ops.isEmpty()) {
        for (        ASTBinaryExpression o : ops) {
          processBinaryExpression(o,data);
        }
      }
    }
  break;
case 2:
{
  final ASTVariableExpression right=reverseOrder ? nodes.get(0) : nodes.get(1);
  if (urlParameterStrings.contains(Helper.getFQVariableName(right))) {
    addViolation(data,right);
  }
}
break;
default :
break;
}
}
