@Provides @MetricScope public Set<MetricBackend> backends(List<MetricModule.Exposed> components,MetricBackendReporter reporter){
  return ImmutableSet.copyOf(components.stream().map(MetricModule.Exposed::backend).map(reporter::decorate).iterator());
}
