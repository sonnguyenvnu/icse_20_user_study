public void validate(final EventTypeBase eventType) throws InvalidEventTypeException {
  if (eventType.getCategory() == EventCategory.UNDEFINED && !eventType.getEnrichmentStrategies().isEmpty()) {
    throw new InvalidEventTypeException("must not define enrichment strategy for undefined event type");
  }
  final Set<EnrichmentStrategyDescriptor> uniqueStrategies=new HashSet<>(eventType.getEnrichmentStrategies());
  if (eventType.getEnrichmentStrategies().size() != uniqueStrategies.size()) {
    throw new InvalidEventTypeException("enrichment strategies must not contain duplicated entries");
  }
  if (eventType.getCategory() != EventCategory.UNDEFINED && !eventType.getEnrichmentStrategies().contains(EnrichmentStrategyDescriptor.METADATA_ENRICHMENT)) {
    throw new InvalidEventTypeException("must define metadata enrichment strategy");
  }
}
