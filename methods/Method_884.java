private void checkThreadLocal(ASTFieldDeclaration fieldDeclaration,ASTCompilationUnit node,Object data){
  try {
    String variableName=VariableUtils.getVariableName(fieldDeclaration);
    List<Node> nodes=node.findChildNodesWithXPath(String.format(XPATH_TPL,variableName));
    if (nodes == null || nodes.isEmpty()) {
      ViolationUtils.addViolationWithPrecisePosition(this,fieldDeclaration,data,I18nResources.getMessage("java.concurrent.ThreadLocalShouldRemoveRule.violation.msg",variableName));
    }
  }
 catch (  JaxenException ignore) {
  }
}
