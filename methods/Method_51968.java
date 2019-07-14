@Override public double computeFor(ASTAnyTypeDeclaration node,MetricOptions options){
  JavaFieldSigMask mask=new JavaFieldSigMask();
  mask.restrictVisibilitiesTo(Visibility.PUBLIC);
  return (double)countMatchingFieldSigs(node,mask);
}
