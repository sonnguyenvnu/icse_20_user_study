static String getFQVariableName(final ASTNewKeyValueObjectExpression variable){
  StringBuilder sb=new StringBuilder().append(variable.getNode().getDefiningType().getApexName()).append(":").append(variable.getType());
  return sb.toString();
}
