@Override public void enrich(final BatchItem batchItem,final EventType eventType) throws EnrichmentException {
  try {
    final JSONObject metadata=batchItem.getEvent().getJSONObject(BatchItem.Injection.METADATA.name);
    setReceivedAt(metadata);
    setEventTypeName(metadata,eventType);
    setFlowId(metadata);
    setPartition(metadata,batchItem);
    setVersion(metadata,eventType);
    batchItem.inject(BatchItem.Injection.METADATA,metadata.toString());
  }
 catch (  final JSONException e) {
    throw new EnrichmentException("enrichment error",e);
  }
}
