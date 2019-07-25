private EventPublishResult failed(final List<BatchItem> batch){
  return new EventPublishResult(EventPublishingStatus.FAILED,EventPublishingStep.PUBLISHING,responses(batch));
}
