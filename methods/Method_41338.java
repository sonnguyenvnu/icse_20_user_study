public boolean canHandleTriggerType(OperableTrigger trigger){
  return ((trigger instanceof SimpleTriggerImpl) && !((SimpleTriggerImpl)trigger).hasAdditionalProperties());
}
