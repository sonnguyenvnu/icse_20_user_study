/** 
 * check if any method matches queryForList(String statementName,int start,int size)
 */
private void visitPrimaryExpression(ASTPrimaryExpression node,Object data,Set<String> sqlMapFields) throws JaxenException {
  List<Node> astNames=node.findChildNodesWithXPath(PRIMARY_METHOD_NAME_XPATH);
  for (  Node astName : astNames) {
    String methodName=astName.getImage();
    if (!(StringUtils.isNotEmpty(methodName) && methodName.contains(IBATIS_QUERY_FOR_LIST_METHOD_NAME))) {
      continue;
    }
    String methodInvokeName=methodName.substring(0,methodName.indexOf(IBATIS_QUERY_FOR_LIST_METHOD_NAME));
    if (StringUtils.isEmpty(methodInvokeName)) {
      continue;
    }
    if (!sqlMapFields.contains(methodInvokeName)) {
      continue;
    }
    List<Node> literals=node.findChildNodesWithXPath(PRIMARY_METHOD_ARGUMENT_XPATH);
    if (literals == null || (literals.size() != LITERALS_SIZE)) {
      continue;
    }
    boolean firstMethodArgumentString="java.lang.String".equals(((ASTLiteral)(literals.get(0))).getType().getName());
    boolean secondMethodArgumentInt="int".equals(((ASTLiteral)(literals.get(1))).getType().getName());
    boolean thirdMethodArgumentInt="int".equals(((ASTLiteral)(literals.get(2))).getType().getName());
    if (firstMethodArgumentString && secondMethodArgumentInt && thirdMethodArgumentInt) {
      ViolationUtils.addViolationWithPrecisePosition(this,node,data,I18nResources.getMessage("java.naming.IbatisMethodQueryForListRule.violation.msg"));
    }
  }
}
