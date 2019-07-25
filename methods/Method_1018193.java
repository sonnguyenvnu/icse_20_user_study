public void delay(ProvenanceEventRecordDTOHolder eventHolder,List<ProvenanceEventRecordDTO> unregisteredEvents){
  if (enabled) {
    RetryProvenanceEventRecordHolder holder=null;
    if (eventHolder instanceof RetryProvenanceEventRecordHolder) {
      holder=(RetryProvenanceEventRecordHolder)eventHolder;
      holder.setLastRetryTime(DateTime.now());
      holder.incrementRetryAttempt();
    }
 else {
      holder=new RetryProvenanceEventRecordHolder(maxRetries);
    }
    holder.setEvents(unregisteredEvents);
    if (receiver != null && !queue.contains(holder)) {
      if (!queue.offer(holder)) {
        throw new RuntimeException("Error adding item to the queue");
      }
    }
  }
}
