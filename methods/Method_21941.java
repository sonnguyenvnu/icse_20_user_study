@Override public Optional<EventTraceDataSourceConfiguration> loadActivated(){
  return Optional.fromNullable(findActivatedDataSourceConfiguration(loadGlobal()));
}
