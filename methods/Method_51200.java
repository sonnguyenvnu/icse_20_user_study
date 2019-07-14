@Override public MetricMemoizer<T> getClassMemoizer(QualifiedName qname){
  return DummyMetricMemoizer.getInstance();
}
