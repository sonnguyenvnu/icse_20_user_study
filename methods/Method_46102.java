/** 
 * Notify consumer.
 * @param newCache the new cache
 */
void notifyConsumer(Map<String,ProviderGroup> newCache){
  Map<String,ProviderGroup> oldCache=memoryCache;
  MapDifference<String,ProviderGroup> difference=new MapDifference<String,ProviderGroup>(newCache,oldCache);
  Map<String,ProviderGroup> onlynew=difference.entriesOnlyOnLeft();
  for (  Map.Entry<String,ProviderGroup> entry : onlynew.entrySet()) {
    notifyConsumerListeners(entry.getKey(),entry.getValue());
  }
  Map<String,ProviderGroup> onlyold=difference.entriesOnlyOnRight();
  for (  Map.Entry<String,ProviderGroup> entry : onlyold.entrySet()) {
    notifyConsumerListeners(entry.getKey(),new ProviderGroup());
  }
  Map<String,ValueDifference<ProviderGroup>> changed=difference.entriesDiffering();
  for (  Map.Entry<String,ValueDifference<ProviderGroup>> entry : changed.entrySet()) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("{} has differente",entry.getKey());
    }
    ValueDifference<ProviderGroup> differentValue=entry.getValue();
    ProviderGroup innew=differentValue.leftValue();
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("new(right) is {}",innew);
    }
    notifyConsumerListeners(entry.getKey(),innew);
  }
}
