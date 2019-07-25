@Override public MVMap.Decision decide(Object[] existingValue,Object[] providedValue){
  assert decision == null;
  if (existingValue == null) {
    decision=MVMap.Decision.ABORT;
  }
 else {
    VersionedValue valueToRestore=(VersionedValue)existingValue[2];
    long operationId;
    if (valueToRestore == null || (operationId=valueToRestore.getOperationId()) == 0 || TransactionStore.getTransactionId(operationId) == transactionId && TransactionStore.getLogId(operationId) < toLogId) {
      int mapId=(Integer)existingValue[0];
      MVMap<Object,VersionedValue> map=store.openMap(mapId);
      if (map != null && !map.isClosed()) {
        Object key=existingValue[1];
        VersionedValue previousValue=map.operate(key,valueToRestore,MVMap.DecisionMaker.DEFAULT);
        listener.onRollback(map,key,previousValue,valueToRestore);
      }
    }
    decision=MVMap.Decision.REMOVE;
  }
  return decision;
}
