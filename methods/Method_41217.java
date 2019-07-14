public boolean canHandleTriggerType(OperableTrigger trigger){
  return ((trigger instanceof CalendarIntervalTriggerImpl) && !((CalendarIntervalTriggerImpl)trigger).hasAdditionalProperties());
}
