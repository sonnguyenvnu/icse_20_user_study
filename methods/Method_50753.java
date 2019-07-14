private String getReturnType(final ASTMethod method){
  return new StringBuilder().append(method.getNode().getDefiningType().getApexName()).append(":").append(method.getNode().getMethodInfo().getEmitSignature().getReturnType().getApexName()).toString();
}
