@Override public double computeFor(ASTAnyTypeDeclaration node,MetricOptions options){
  return JavaMetrics.get(JavaOperationMetricKey.CYCLO,node,options,ResultOption.SUM);
}
