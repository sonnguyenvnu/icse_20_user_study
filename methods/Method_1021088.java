/** 
 * Gets data from current event consumers. It tries to use as less list allocations as it is possible.
 * @return List of consumed events.
 */
private List<ConsumedEvent> poll(){
  List<ConsumedEvent> result=null;
  boolean newCollectionCreated=false;
  for (  final EventConsumer consumer : eventConsumers.values()) {
    final List<ConsumedEvent> partialResult=consumer.readEvents();
    if (null == result) {
      result=partialResult;
    }
 else {
      if (!newCollectionCreated) {
        result=new ArrayList<>(result);
        newCollectionCreated=true;
      }
      result.addAll(partialResult);
    }
  }
  return result == null ? Collections.emptyList() : result;
}
