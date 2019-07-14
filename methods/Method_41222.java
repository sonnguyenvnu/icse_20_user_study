public boolean canHandleTriggerType(OperableTrigger trigger){
  return ((trigger instanceof CronTriggerImpl) && !((CronTriggerImpl)trigger).hasAdditionalProperties());
}
