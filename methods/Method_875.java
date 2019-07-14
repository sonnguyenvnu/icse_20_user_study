private void checkRandom(ASTFieldDeclaration fieldDeclaration,List<ASTMethodDeclaration> methodDeclarations,Object data){
  for (  ASTMethodDeclaration methodDeclaration : methodDeclarations) {
    try {
      List<Node> nodes=methodDeclaration.findChildNodesWithXPath(String.format(XPATH_TPL,VariableUtils.getVariableName(fieldDeclaration)));
      if (nodes == null || nodes.isEmpty()) {
        continue;
      }
      for (      Node rvNode : nodes) {
        addViolationWithMessage(data,rvNode,MESSAGE_KEY_PREFIX + ".violation.msg.random",new Object[]{rvNode.getImage()});
      }
    }
 catch (    JaxenException ignore) {
    }
  }
}
