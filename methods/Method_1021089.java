@Override public void reassign(final Collection<NakadiCursor> newValues) throws InvalidCursorException {
  final Map<EventTypePartition,NakadiCursor> newCursorMap=newValues.stream().collect(Collectors.toMap(NakadiCursor::getEventTypePartition,Function.identity()));
  final NakadiCollectionUtils.Diff<EventTypePartition> partitionsDiff=NakadiCollectionUtils.difference(latestOffsets.keySet(),newCursorMap.keySet());
  final NakadiCollectionUtils.Diff<String> eventTypesDiff=NakadiCollectionUtils.difference(timelineRefreshListeners.keySet(),newValues.stream().map(NakadiCursor::getEventType).collect(Collectors.toSet()));
  cleanStreamedPartitions(partitionsDiff.getRemoved());
  eventTypesDiff.getRemoved().stream().map(timelineRefreshListeners::remove).forEach(TimelineSync.ListenerRegistration::cancel);
  partitionsDiff.getAdded().forEach(etp -> latestOffsets.put(etp,newCursorMap.get(etp)));
  eventTypesDiff.getAdded().forEach(item -> timelineRefreshListeners.put(item,timelineSync.registerTimelineChangeListener(item,this::onTimelineChange)));
  onTimelinesChanged();
}
