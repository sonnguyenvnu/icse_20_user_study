private static double getClassMetric(ASTAnyTypeDeclaration node,JavaClassMetricKey key){
  return JavaMetrics.get(key,node);
}
