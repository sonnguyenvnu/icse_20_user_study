@Override public TriggerWrapper createTriggerWrapper(OperableTrigger trigger,boolean jobDisallowsConcurrence){
  return new TriggerWrapper(trigger,jobDisallowsConcurrence);
}
