protected void sleepAfterWrite(StoreTransaction txh,MaskedTimestamp mustPass) throws BackendException {
  assert mustPass.getDeletionTime(times) < mustPass.getAdditionTime(times);
  try {
    times.sleepPast(mustPass.getAdditionTimeInstant(times));
  }
 catch (  InterruptedException e) {
    throw new PermanentBackendException("Unexpected interrupt",e);
  }
}
