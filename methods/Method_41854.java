List<TriggerWrapper> getNextTriggerWrappers(final long noLaterThan,final int maxCount,final long timeWindow) throws JobPersistenceException {
  return getNextTriggerWrappers(timeTriggers,noLaterThan,maxCount,timeWindow);
}
