OperableTrigger markAndCloneTrigger(final TriggerWrapper tw){
  tw.setState(TriggerState.ACQUIRED,terracottaClientId,triggerFacade);
  String firedInstanceId=terracottaClientId + "-" + String.valueOf(ftrCtr++);
  tw.setFireInstanceId(firedInstanceId,triggerFacade);
  return tw.getTriggerClone();
}
