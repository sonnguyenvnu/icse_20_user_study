void storeExpectedValue(ExpectedValueCheckingStore store,KeyColumn lockID,StaticBuffer value){
  Preconditions.checkNotNull(store);
  Preconditions.checkNotNull(lockID);
  lockedOn(store);
  Map<KeyColumn,StaticBuffer> m=expectedValuesByStore.get(store);
  assert null != m;
  if (m.containsKey(lockID)) {
    log.debug("Multiple expected values for {}: keeping initial value {} and discarding later value {}",lockID,m.get(lockID),value);
  }
 else {
    m.put(lockID,value);
    log.debug("Store expected value for {}: {}",lockID,value);
  }
}
