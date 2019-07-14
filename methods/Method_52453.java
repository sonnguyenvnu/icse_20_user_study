public String getParameterDisplaySignature(){
  StringBuilder sb=new StringBuilder("(");
  ASTFormalParameters params=(ASTFormalParameters)node.jjtGetChild(0);
  for (int i=0; i < ((ASTMethodDeclarator)node).getParameterCount(); i++) {
    ASTFormalParameter p=(ASTFormalParameter)params.jjtGetChild(i);
    sb.append(p.getTypeNode().getTypeImage());
    if (p.isVarargs()) {
      sb.append("...");
    }
    sb.append(',');
  }
  if (sb.charAt(sb.length() - 1) == ',') {
    sb.deleteCharAt(sb.length() - 1);
  }
  sb.append(')');
  return sb.toString();
}
