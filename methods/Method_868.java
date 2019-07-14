private void handleMethod(ASTMethodDeclaration methodDeclaration,Object data){
  DataFlowNode dataFlowNode=methodDeclaration.getDataFlowNode();
  if (dataFlowNode == null || dataFlowNode.getFlow() == null) {
    return;
  }
  Stack<Node> stack=new Stack<>();
  Set<String> localSimpleDateFormatNames=new HashSet<>();
  for (  DataFlowNode flowNode : dataFlowNode.getFlow()) {
    handleFlowNode(stack,localSimpleDateFormatNames,flowNode);
  }
  while (!stack.isEmpty()) {
    Node node=stack.pop();
    if (node instanceof ASTPrimaryExpression) {
      addViolationWithMessage(data,node,"java.concurrent.AvoidCallStaticSimpleDateFormatRule.violation.msg",new Object[]{getExpressName((ASTPrimaryExpression)node)});
    }
  }
}
