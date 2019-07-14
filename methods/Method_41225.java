public boolean canHandleTriggerType(OperableTrigger trigger){
  return ((trigger instanceof DailyTimeIntervalTrigger) && !((DailyTimeIntervalTriggerImpl)trigger).hasAdditionalProperties());
}
