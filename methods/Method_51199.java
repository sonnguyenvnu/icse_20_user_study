@Override public MetricMemoizer<O> getOperationMemoizer(QualifiedName qname){
  return DummyMetricMemoizer.getInstance();
}
