@Override public StatisticsComponent module(final EarlyComponent early){
  return DaggerNoopStatisticsComponent.builder().earlyComponent(early).noopStatisticsModule(this).build();
}
