@Override public boolean isExecuteMisfired(final Collection<Integer> shardingItems){
  return isEligibleForJobRunning() && configService.load(true).getTypeConfig().getCoreConfig().isMisfire() && !executionService.getMisfiredJobItems(shardingItems).isEmpty();
}
