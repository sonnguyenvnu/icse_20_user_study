@Override public boolean removeTriggers(List<TriggerKey> triggerKeys) throws JobPersistenceException {
  return clusteredJobStore.removeTriggers(triggerKeys);
}
