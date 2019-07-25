public void delay(AggregatedFeedProcessorStatisticsHolder statsHolder,List<AggregatedFeedProcessorStatistics> unregisteredEvents){
  if (enabled) {
    RetryAggregatedFeedProcessorStatisticsHolder holder=null;
    if (statsHolder instanceof RetryAggregatedFeedProcessorStatisticsHolder) {
      holder=(RetryAggregatedFeedProcessorStatisticsHolder)statsHolder;
      holder.setLastRetryTime(DateTime.now());
      holder.incrementRetryAttempt();
    }
 else {
      holder=new RetryAggregatedFeedProcessorStatisticsHolder(maxRetries);
    }
    holder.setFeedStatistics(unregisteredEvents);
    if (statsJmsReceiver != null && !statsQueue.contains(holder)) {
      if (!statsQueue.offer(holder)) {
        throw new RuntimeException("Error adding item to the queue");
      }
    }
  }
}
