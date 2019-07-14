/** 
 * @see org.quartz.spi.JobStore#getPausedTriggerGroups()
 */
@Override public Set<String> getPausedTriggerGroups() throws JobPersistenceException {
  lock();
  try {
    return new HashSet<String>(triggerFacade.allPausedTriggersGroupNames());
  }
  finally {
    unlock();
  }
}
