public TriggerPersistenceDelegate findTriggerPersistenceDelegate(String discriminator){
  for (  TriggerPersistenceDelegate delegate : triggerPersistenceDelegates) {
    if (delegate.getHandledTriggerTypeDiscriminator().equals(discriminator))     return delegate;
  }
  return null;
}
