public void enrich(final BatchItem batchItem,final EventType eventType) throws EnrichmentException {
  for (  final EnrichmentStrategyDescriptor descriptor : eventType.getEnrichmentStrategies()) {
    final EnrichmentStrategy strategy=getStrategy(descriptor);
    strategy.enrich(batchItem,eventType);
  }
}
