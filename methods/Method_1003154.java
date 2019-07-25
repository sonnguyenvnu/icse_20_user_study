@Override public MVMap.Decision decide(VersionedValue existingValue,VersionedValue providedValue){
  assert decision == null;
  if (existingValue == null || existingValue.getOperationId() != undoKey) {
    decision=MVMap.Decision.ABORT;
  }
 else   if (existingValue.getCurrentValue() == null) {
    decision=MVMap.Decision.REMOVE;
  }
 else {
    decision=MVMap.Decision.PUT;
  }
  return decision;
}
