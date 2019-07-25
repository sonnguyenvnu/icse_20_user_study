@Override public StatisticsComponent module(final EarlyComponent early){
  return DaggerSemanticStatisticsComponent.builder().earlyComponent(early).semanticStatisticsModule(this).build();
}
