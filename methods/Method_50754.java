private boolean isMethodAGetter(final ASTMethod method){
  final boolean startsWithGet=method.getNode().getMethodInfo().getCanonicalName().startsWith("get");
  final boolean voidOrString=VOID_OR_STRING_PATTERN.matcher(method.getNode().getMethodInfo().getEmitSignature().getReturnType().getApexName()).matches();
  final boolean noParams=method.findChildrenOfType(ASTParameter.class).isEmpty();
  return startsWithGet && noParams && !voidOrString;
}
