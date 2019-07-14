@Override public double computeFor(ASTAnyTypeDeclaration node,MetricOptions options){
  Map<String,Set<String>> usagesByMethod=new TccAttributeAccessCollector(node).start();
  int numPairs=numMethodsRelatedByAttributeAccess(usagesByMethod);
  int maxPairs=maxMethodPairs(usagesByMethod.size());
  return numPairs / (double)maxPairs;
}
