public int getParameterCount(){
  final List<ASTFormalParameter> parameters=findChildrenOfType(ASTFormalParameter.class);
  return !parameters.isEmpty() && parameters.get(0).isExplicitReceiverParameter() ? parameters.size() - 1 : parameters.size();
}
