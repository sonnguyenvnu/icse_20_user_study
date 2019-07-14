private Description identifyDiffieHellmanAndDsaVulnerabilities(MethodInvocationTree tree){
  Object argument=ASTHelpers.constValue((JCTree)tree.getArguments().get(0));
  if (argument == null) {
    return buildErrorMessage(tree,"the algorithm specification is not a compile-time constant expression");
  }
  String algorithm=(String)argument;
  if (algorithm.matches("DH")) {
    return buildErrorMessage(tree,"using Diffie-Hellman on prime fields is insecure");
  }
  if (algorithm.matches("DSA")) {
    return buildErrorMessage(tree,"using DSA is insecure");
  }
  return Description.NO_MATCH;
}
