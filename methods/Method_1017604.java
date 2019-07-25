/** 
 * Release lock on this connection presumably held by given object.
 * @param holder object that holds the lock. Normally current thread.
 * @throws PSQLException when this thread does not hold the lock
 */
private void unlock(Object holder) throws PSQLException {
  if (lockedFor != holder) {
    throw new PSQLException(GT.tr("Tried to break lock on database connection"),PSQLState.OBJECT_NOT_IN_STATE);
  }
  lockedFor=null;
  this.notify();
}
