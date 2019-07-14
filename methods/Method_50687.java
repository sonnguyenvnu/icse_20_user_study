@Override public double computeFor(ASTUserClassOrInterface<?> node,MetricOptions options){
  return ApexMetrics.get(ApexOperationMetricKey.CYCLO,node,ResultOption.SUM);
}
