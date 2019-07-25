@Override public void retry(String savepointId,Lock lock) throws InvalidLockException, InvalidSetpointException {
  if (!isValidLock(lock)) {
    throw new InvalidLockException();
  }
  logger.debug("Retry savepointId {}",savepointId);
  SavepointEntry entry=lookupEntryWithGuarantee(savepointId);
  entry.retry();
  savePoints.put(savepointId,entry);
}
