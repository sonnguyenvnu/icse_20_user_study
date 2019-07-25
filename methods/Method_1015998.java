private int lookup(final Services service,final GridQueue[] queues,final String hashingKey) throws IOException {
  if (queues.length == 1)   return 0;
  Map<String,Integer> lookupMap=this.leastFilledLookup.get(service);
  if (lookupMap == null) {
    lookupMap=new ConcurrentHashMap<>();
    this.leastFilledLookup.put(service,lookupMap);
  }
  Integer lookupIndex=lookupMap.get(hashingKey);
  if (lookupIndex == null) {
    AvailableContainer[] available=available(service,queues);
    lookupIndex=leastFilled(available);
    lookupMap.put(hashingKey,lookupIndex);
  }
  return lookupIndex;
}
