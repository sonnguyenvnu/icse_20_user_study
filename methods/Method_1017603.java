/** 
 * Obtain lock over this connection for given object, blocking to wait if necessary.
 * @param obtainer object that gets the lock. Normally current thread.
 * @throws PSQLException when already holding the lock or getting interrupted.
 */
private void lock(Object obtainer) throws PSQLException {
  if (lockedFor == obtainer) {
    throw new PSQLException(GT.tr("Tried to obtain lock while already holding it"),PSQLState.OBJECT_NOT_IN_STATE);
  }
  waitOnLock();
  lockedFor=obtainer;
}
