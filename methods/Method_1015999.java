private int balance(final Services service,final GridQueue[] queues,final String hashingKey) throws IOException {
  if (queues.length == 1)   return 0;
  Map<String,Integer> lookupMap=this.leastFilledLookup.get(service);
  if (lookupMap == null) {
    lookupMap=new ConcurrentHashMap<>();
    this.leastFilledLookup.put(service,lookupMap);
  }
  Integer lookupIndex=lookupMap.get(hashingKey);
  AvailableContainer[] available=available(service,queues);
  assert available.length == queues.length;
  int leastFilled=leastFilled(available);
  assert leastFilled < queues.length;
  if (lookupIndex == null) {
    lookupIndex=leastFilled;
    lookupMap.put(hashingKey,lookupIndex);
  }
 else {
    Set<String> switchedIDs=switchedIDsMap.get(service);
    if (switchedIDs == null) {
      switchedIDs=ConcurrentHashMap.newKeySet();
      switchedIDsMap.put(service,switchedIDs);
    }
    if (available[leastFilled].getAvailable() == 0 && !switchedIDs.contains(hashingKey)) {
      switchedIDs.add(hashingKey);
      Data.logger.info("AbstractBroker switching " + hashingKey + " from " + lookupIndex + " to " + leastFilled);
      lookupIndex=leastFilled;
      lookupMap.put(hashingKey,lookupIndex);
    }
  }
  assert lookupIndex < queues.length;
  return lookupIndex;
}
