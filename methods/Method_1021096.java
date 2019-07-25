private void submit(final List<BatchItem> batch,final EventType eventType) throws EventPublishingException {
  final Timeline activeTimeline=timelineService.getActiveTimeline(eventType);
  timelineService.getTopicRepository(eventType).syncPostBatch(activeTimeline.getTopic(),batch);
}
