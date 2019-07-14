/** 
 * <p> Get the names of all of the <code> {@link org.quartz.Trigger}</code> groups. </p>
 */
@Override public List<String> getTriggerGroupNames() throws JobPersistenceException {
  lock();
  try {
    return new ArrayList<String>(triggerFacade.allTriggersGroupNames());
  }
  finally {
    unlock();
  }
}
