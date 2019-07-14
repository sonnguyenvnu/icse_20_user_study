@Override public void addViolation(Object data,Node node,String arg){
  if (node instanceof ASTVariableDeclaratorId) {
    ViolationUtils.addViolationWithPrecisePosition(this,node,data,I18nResources.getMessage("java.naming.BooleanPropertyShouldNotStartWithIsRule.violation.msg",node.getImage()));
  }
 else {
    super.addViolation(data,node,arg);
  }
}
