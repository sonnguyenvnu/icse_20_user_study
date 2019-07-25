public static GroupedStats add(GroupedStats stats,ProvenanceEventRecordDTO event){
  Long eventId=event.getEventId();
  if (stats instanceof GroupedStatsV2) {
    ((GroupedStatsV2)stats).setLatestFlowFileId(event.getFlowFileUuid());
  }
  stats.addTotalCount(1L);
  stats.addBytesIn(event.getInputContentClaimFileSizeBytes() != null ? event.getInputContentClaimFileSizeBytes() : 0L);
  stats.addBytesOut(event.getOutputContentClaimFileSizeBytes() != null ? event.getOutputContentClaimFileSizeBytes() : 0L);
  stats.addDuration(event.getEventDuration());
  stats.setSourceConnectionIdentifier(event.getSourceConnectionIdentifier());
  if (event.isFailure()) {
    stats.addProcessorsFailed(1L);
  }
  if (event.isStartOfJob()) {
    stats.addJobsStarted(1L);
  }
  stats.setTime(event.getEventTime());
  if (stats.getMinTime() == null) {
    stats.setMinTime(event.getEventTime());
  }
  if (stats.getMaxTime() == null) {
    stats.setMaxTime(event.getEventTime());
  }
  if (event.getEventTime() > stats.getMaxTime()) {
    stats.setMaxTime(event.getEventTime());
  }
  if (event.getEventTime() < stats.getMinTime()) {
    stats.setMinTime(event.getEventTime());
  }
  if (Math.abs(stats.getMaxEventId()) < Math.abs(eventId)) {
    stats.setMaxEventId(eventId);
  }
  return stats;
}
