@PostConstruct private void init(){
  retryProvenanceEventWithDelay.setStatsJmsReceiver(this);
  scheduleStatsCompaction();
}
