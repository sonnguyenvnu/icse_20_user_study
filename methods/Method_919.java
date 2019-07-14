private void visitByCollections(ASTClassOrInterfaceDeclaration node,Object data,String collectionType) throws JaxenException {
  String collectionArgXpath="//AllocationExpression/ClassOrInterfaceType[@Image='" + collectionType + "']/../Arguments";
  List<Node> argumentsNodes=node.findChildNodesWithXPath(collectionArgXpath);
  for (  Node argNode : argumentsNodes) {
    if (!(argNode instanceof ASTArguments)) {
      continue;
    }
    if (argNode.getFirstParentOfType(ASTMethodDeclaration.class) == null) {
      continue;
    }
    ASTArguments argumentNode=(ASTArguments)argNode;
    Integer count=argumentNode.getArgumentCount();
    if (count == 0) {
      addViolationWithMessage(data,argNode,"java.set.CollectionInitShouldAssignCapacityRule.violation.msg",new Object[]{collectionType});
    }
  }
}
