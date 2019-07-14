@Override public Map<MetricKey<?>,Double> computeAllMetricsFor(Node node){
  Map<MetricKey<?>,Double> results=new HashMap<>();
  T t=asTypeNode(node);
  if (t != null) {
    for (    MetricKey<T> tkey : getAvailableTypeMetrics()) {
      results.put(tkey,computeForType(tkey,t,MetricOptions.emptyOptions()));
    }
  }
  O o=asOperationNode(node);
  if (o != null) {
    for (    MetricKey<O> okey : getAvailableOperationMetrics()) {
      results.put(okey,computeForOperation(okey,o,MetricOptions.emptyOptions()));
    }
  }
  return results;
}
