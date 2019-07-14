/** 
 * Determine whether a  {@link Trigger} with the given identifier already exists within the scheduler.
 * @param triggerKey the identifier to check for
 * @return true if a Trigger exists with the given identifier
 * @throws JobPersistenceException
 */
public boolean checkExists(TriggerKey triggerKey) throws JobPersistenceException {
synchronized (lock) {
    TriggerWrapper tw=triggersByKey.get(triggerKey);
    return (tw != null);
  }
}
