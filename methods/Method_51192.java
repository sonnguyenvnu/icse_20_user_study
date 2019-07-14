@Override public MetricMemoizer<T> getClassMemoizer(QualifiedName qname){
synchronized (classesSynchronizer) {
    if (!classes.containsKey(qname)) {
      classes.put(qname,new BasicMetricMemoizer<T>());
    }
  }
  return classes.get(qname);
}
