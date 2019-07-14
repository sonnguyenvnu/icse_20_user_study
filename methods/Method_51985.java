@Override public double computeFor(ASTAnyTypeDeclaration node,MetricOptions options){
  JavaOperationSigMask mask=new JavaOperationSigMask();
  mask.forbid(Role.CONSTRUCTOR,Role.GETTER_OR_SETTER);
  mask.restrictVisibilitiesTo(Visibility.PUBLIC);
  int functionalMethods=countMatchingOpSigs(node,mask);
  mask.coverAllRoles();
  int totalPublicMethods=countMatchingOpSigs(node,mask);
  return functionalMethods / (double)totalPublicMethods;
}
