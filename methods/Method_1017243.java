@Override public MetricBackend decorate(final MetricBackend backend){
  return new InstrumentedMetricBackend(backend);
}
