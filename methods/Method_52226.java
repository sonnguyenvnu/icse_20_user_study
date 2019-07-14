private boolean interfaceRevealsData(ASTAnyTypeDeclaration node){
  double woc=JavaMetrics.get(JavaClassMetricKey.WOC,node);
  return woc < WOC_LEVEL;
}
