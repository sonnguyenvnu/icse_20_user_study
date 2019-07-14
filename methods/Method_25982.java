private Description identifyEcbVulnerability(MethodInvocationTree tree){
  Object argument=ASTHelpers.constValue((JCTree)tree.getArguments().get(0));
  if (argument == null) {
    return buildErrorMessage(tree,"the transformation is not a compile-time constant expression");
  }
  String transformation=(String)argument;
  if (transformation.matches("ARCFOUR.*") || transformation.matches("ARC4.*") || transformation.matches("RC4.*")) {
    return Description.NO_MATCH;
  }
  if (!transformation.matches(".*/.*/.*")) {
    return buildErrorMessage(tree,"the mode and padding must be explicitly specified");
  }
  if (transformation.matches(".*/ECB/.*") && !transformation.matches("RSA/.*") && !transformation.matches("AESWrap/.*")) {
    return buildErrorMessage(tree,"ECB mode must not be used");
  }
  if (transformation.matches("ECIES.*") || transformation.matches("DHIES.*")) {
    return buildErrorMessage(tree,"IES-based algorithms use ECB mode and are insecure");
  }
  return Description.NO_MATCH;
}
