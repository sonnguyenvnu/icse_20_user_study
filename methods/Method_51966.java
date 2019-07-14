@Override public double computeFor(ASTAnyTypeDeclaration node,MetricOptions options){
  JavaOperationSigMask mask=new JavaOperationSigMask();
  mask.restrictRolesTo(Role.GETTER_OR_SETTER);
  mask.restrictVisibilitiesTo(Visibility.PUBLIC);
  return (double)countMatchingOpSigs(node,mask);
}
