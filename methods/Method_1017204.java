@Override public AnalyticsComponent module(final PrimaryComponent primary){
  return DaggerBigtableAnalyticsComponent.builder().primaryComponent(primary).bigtableAnalyticsModule(this).build();
}
