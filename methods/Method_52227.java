private boolean classRevealsDataAndLacksComplexity(ASTAnyTypeDeclaration node){
  int nopa=(int)JavaMetrics.get(JavaClassMetricKey.NOPA,node);
  int noam=(int)JavaMetrics.get(JavaClassMetricKey.NOAM,node);
  int wmc=(int)JavaMetrics.get(JavaClassMetricKey.WMC,node);
  return nopa + noam > ACCESSOR_OR_FIELD_FEW_LEVEL && wmc < WMC_HIGH_LEVEL || nopa + noam > ACCESSOR_OR_FIELD_MANY_LEVEL && wmc < WMC_VERY_HIGH_LEVEL;
}
