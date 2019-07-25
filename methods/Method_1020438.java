@Override public synchronized void add(MetricProducer metricProducer){
  Preconditions.checkNotNull(metricProducer,"metricProducer");
  Set<MetricProducer> newMetricProducers=new LinkedHashSet<MetricProducer>(metricProducers);
  if (!newMetricProducers.add(metricProducer)) {
    return;
  }
  metricProducers=Collections.unmodifiableSet(newMetricProducers);
}
