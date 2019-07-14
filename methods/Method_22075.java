private void removeRunningIfMonitorExecution(final boolean monitorExecution,final List<Integer> shardingItems){
  if (!monitorExecution) {
    return;
  }
  List<Integer> runningShardingItems=new ArrayList<>(shardingItems.size());
  for (  int each : shardingItems) {
    if (isRunning(each)) {
      runningShardingItems.add(each);
    }
  }
  shardingItems.removeAll(runningShardingItems);
}
