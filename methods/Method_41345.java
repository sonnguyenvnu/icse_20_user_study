public TriggerPersistenceDelegate findTriggerPersistenceDelegate(OperableTrigger trigger){
  for (  TriggerPersistenceDelegate delegate : triggerPersistenceDelegates) {
    if (delegate.canHandleTriggerType(trigger))     return delegate;
  }
  return null;
}
