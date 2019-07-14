/** 
 * {@inheritDoc}
 * @throws JobPersistenceException
 */
@Override public boolean checkExists(final TriggerKey triggerKey) throws JobPersistenceException {
  return triggerFacade.containsKey(triggerKey);
}
