public void checkMethodCommentFormat(ASTMethodDeclaration method,Object data){
  Comment comment=method.comment();
  String commentContent=comment.getImage();
  if (EMPTY_CONTENT_PATTERN.matcher(commentContent).matches()) {
    ViolationUtils.addViolationWithPrecisePosition(this,method,data,I18nResources.getMessage(MESSAGE_KEY_PREFIX + ".desc",method.getMethodName()));
  }
  List<Node> variableDeclaratorIds;
  try {
    variableDeclaratorIds=method.findChildNodesWithXPath(METHOD_VARIABLE_DECLARATOR_XPATH);
  }
 catch (  JaxenException e) {
    throw new RuntimeException("XPath expression " + METHOD_VARIABLE_DECLARATOR_XPATH + " failed: " + e.getLocalizedMessage(),e);
  }
  for (  Node variableDeclaratorId : variableDeclaratorIds) {
    ASTVariableDeclaratorId param=(ASTVariableDeclaratorId)variableDeclaratorId;
    String paramName=param.getImage();
    Pattern paramNamePattern=Pattern.compile(".*@param\\s+" + paramName + ".*",Pattern.DOTALL);
    if (!paramNamePattern.matcher(commentContent).matches()) {
      ViolationUtils.addViolationWithPrecisePosition(this,method,data,I18nResources.getMessage(MESSAGE_KEY_PREFIX + ".parameter",method.getMethodName(),paramName));
    }
  }
  if (!method.isVoid() && !RETURN_PATTERN.matcher(commentContent).matches()) {
    ViolationUtils.addViolationWithPrecisePosition(this,method,data,I18nResources.getMessage(MESSAGE_KEY_PREFIX + ".return",method.getMethodName()));
  }
  ASTNameList nameList=method.getThrows();
  if (null != nameList) {
    List<ASTName> exceptions=nameList.findDescendantsOfType(ASTName.class);
    for (    ASTName exception : exceptions) {
      String exceptionName=exception.getImage();
      Pattern exceptionPattern=Pattern.compile(".*@throws\\s+" + exceptionName + ".*",Pattern.DOTALL);
      if (!exceptionPattern.matcher(commentContent).matches()) {
        ViolationUtils.addViolationWithPrecisePosition(this,method,data,I18nResources.getMessage(MESSAGE_KEY_PREFIX + ".exception",method.getMethodName(),exceptionName));
      }
    }
  }
}
