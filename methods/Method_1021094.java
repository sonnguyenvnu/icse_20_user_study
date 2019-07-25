private void enrich(final List<BatchItem> batch,final EventType eventType) throws EnrichmentException {
  for (  final BatchItem batchItem : batch) {
    try {
      batchItem.setStep(EventPublishingStep.ENRICHING);
      enrichment.enrich(batchItem,eventType);
    }
 catch (    EnrichmentException e) {
      batchItem.updateStatusAndDetail(EventPublishingStatus.FAILED,e.getMessage());
      throw e;
    }
  }
}
