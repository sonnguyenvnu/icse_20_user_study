@Override public MetricMemoizer<O> getOperationMemoizer(QualifiedName qname){
synchronized (operationsSynchronizer) {
    if (!operations.containsKey(qname)) {
      operations.put(qname,new BasicMetricMemoizer<O>());
    }
  }
  return operations.get(qname);
}
