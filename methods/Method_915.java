@Override public void addViolation(Object data,Node node,String arg){
  ASTLocalVariableDeclaration localVariableDeclaration=node.getFirstParentOfType(ASTLocalVariableDeclaration.class);
  if (localVariableDeclaration == null) {
    super.addViolation(data,node,arg);
  }
 else {
    ViolationUtils.addViolationWithPrecisePosition(this,node,data,I18nResources.getMessage("java.other.AvoidPatternCompileInMethodRule.violation.msg",VariableUtils.getVariableName(localVariableDeclaration)));
  }
}
