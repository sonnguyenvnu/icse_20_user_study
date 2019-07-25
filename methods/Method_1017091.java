public AnalyticsComponent module(PrimaryComponent primary){
  return DaggerNullAnalyticsComponent.builder().primaryComponent(primary).nullAnalyticsModule(this).build();
}
