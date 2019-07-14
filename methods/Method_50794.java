static String getFQVariableName(final ASTField variable){
  Field n=variable.getNode();
  StringBuilder sb=new StringBuilder().append(n.getDefiningType().getApexName()).append(":").append(n.getFieldInfo().getName());
  return sb.toString();
}
