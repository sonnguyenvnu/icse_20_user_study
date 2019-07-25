@Override public synchronized void remove(MetricProducer metricProducer){
  Preconditions.checkNotNull(metricProducer,"metricProducer");
  Set<MetricProducer> newMetricProducers=new LinkedHashSet<MetricProducer>(metricProducers);
  if (!newMetricProducers.remove(metricProducer)) {
    return;
  }
  metricProducers=Collections.unmodifiableSet(newMetricProducers);
}
