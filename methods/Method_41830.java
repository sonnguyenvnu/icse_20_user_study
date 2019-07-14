/** 
 * <p> Retrieve the given <code> {@link org.quartz.Trigger}</code>. </p>
 * @param triggerKey The key of the <code>Trigger</code> to be retrieved.
 * @return The desired <code>Trigger</code>, or null if there is no match.
 */
@Override public OperableTrigger retrieveTrigger(TriggerKey triggerKey) throws JobPersistenceException {
  lock();
  try {
    TriggerWrapper tw=triggerFacade.get(triggerKey);
    return (tw != null) ? (OperableTrigger)tw.getTriggerClone() : null;
  }
  finally {
    unlock();
  }
}
