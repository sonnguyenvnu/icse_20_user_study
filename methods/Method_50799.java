static String getFQVariableName(ASTParameter p){
  StringBuffer sb=new StringBuffer();
  sb.append(p.getNode().getDefiningType()).append(":").append(p.getImage());
  return sb.toString();
}
