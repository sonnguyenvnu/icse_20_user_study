@Provides @MetadataScope public Set<MetadataBackend> backends(List<Exposed> components,MetadataBackendReporter reporter){
  return ImmutableSet.copyOf(components.stream().map(Exposed::backend).map(reporter::decorate).iterator());
}
