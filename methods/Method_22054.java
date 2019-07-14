@Override public void triggerMisfired(final Trigger trigger){
  if (null != trigger.getPreviousFireTime()) {
    executionService.setMisfire(shardingService.getLocalShardingItems());
  }
}
