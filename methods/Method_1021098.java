public void delete(final String eventTypeName) throws EventTypeDeletionException, AccessDeniedException, NoSuchEventTypeException, ConflictException, ServiceTemporarilyUnavailableException, DbWriteOperationsBlockedException {
  if (featureToggleService.isFeatureEnabled(FeatureToggleService.Feature.DISABLE_DB_WRITE_OPERATIONS)) {
    throw new DbWriteOperationsBlockedException("Cannot delete event type: write operations on DB " + "are blocked by feature flag.");
  }
  Closeable deletionCloser=null;
  final EventType eventType;
  final Multimap<TopicRepository,String> topicsToDelete;
  try {
    deletionCloser=timelineSync.workWithEventType(eventTypeName,nakadiSettings.getTimelineWaitTimeoutMs());
    final Optional<EventType> eventTypeOpt=eventTypeRepository.findByNameO(eventTypeName);
    if (!eventTypeOpt.isPresent()) {
      throw new NoSuchEventTypeException("EventType \"" + eventTypeName + "\" does not exist.");
    }
    eventType=eventTypeOpt.get();
    authorizationValidator.authorizeEventTypeView(eventType);
    authorizationValidator.authorizeEventTypeAdmin(eventType);
    if (featureToggleService.isFeatureEnabled(DELETE_EVENT_TYPE_WITH_SUBSCRIPTIONS)) {
      topicsToDelete=deleteEventTypeWithSubscriptions(eventTypeName);
    }
 else {
      topicsToDelete=deleteEventTypeIfNoSubscriptions(eventTypeName);
    }
  }
 catch (  final InterruptedException e) {
    Thread.currentThread().interrupt();
    LOG.error("Failed to wait for timeline switch",e);
    throw new EventTypeUnavailableException("Event type " + eventTypeName + " is currently in maintenance, please repeat request");
  }
catch (  final TimeoutException e) {
    LOG.error("Failed to wait for timeline switch",e);
    throw new EventTypeUnavailableException("Event type " + eventTypeName + " is currently in maintenance, please repeat request");
  }
catch (  final InternalNakadiException|ServiceTemporarilyUnavailableException e) {
    LOG.error("Error deleting event type " + eventTypeName,e);
    throw new EventTypeDeletionException("Failed to delete event type " + eventTypeName);
  }
 finally {
    try {
      if (deletionCloser != null) {
        deletionCloser.close();
      }
    }
 catch (    final IOException e) {
      LOG.error("Exception occurred when releasing usage of event-type",e);
    }
  }
  if (topicsToDelete != null) {
    for (    final TopicRepository topicRepository : topicsToDelete.keySet()) {
      for (      final String topic : topicsToDelete.get(topicRepository)) {
        try {
          topicRepository.deleteTopic(topic);
        }
 catch (        TopicDeletionException e) {
          LOG.info("Could not delete topic " + topic,e);
        }
      }
    }
  }
  nakadiKpiPublisher.publish(etLogEventType,() -> new JSONObject().put("event_type",eventTypeName).put("status","deleted").put("category",eventType.getCategory()).put("authz",identifyAuthzState(eventType)).put("compatibility_mode",eventType.getCompatibilityMode()));
  nakadiAuditLogPublisher.publish(Optional.of(eventType),Optional.empty(),NakadiAuditLogPublisher.ResourceType.EVENT_TYPE,NakadiAuditLogPublisher.ActionType.DELETED,eventType.getName());
}
