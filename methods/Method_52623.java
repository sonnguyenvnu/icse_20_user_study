private static double getOpMetric(MethodLikeNode node,JavaOperationMetricKey key){
  return JavaMetrics.get(key,node);
}
