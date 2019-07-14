public boolean isVarargs(){
  ASTFormalParameters params=(ASTFormalParameters)node.jjtGetChild(0);
  if (params.getParameterCount() == 0) {
    return false;
  }
  ASTFormalParameter p=(ASTFormalParameter)params.jjtGetChild(params.getParameterCount() - 1);
  return p.isVarargs();
}
