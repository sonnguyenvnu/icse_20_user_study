@Override public void resetTriggerFromErrorState(final TriggerKey triggerKey) throws JobPersistenceException {
  TriggerWrapper tw=triggerFacade.get(triggerKey);
  if (tw == null) {
    return;
  }
  if (tw.getState() != TriggerState.ERROR) {
    return;
  }
  if (triggerFacade.pausedGroupsContain(triggerKey.getGroup())) {
    tw.setState(TriggerState.PAUSED,terracottaClientId,triggerFacade);
  }
 else {
    tw.setState(TriggerState.WAITING,terracottaClientId,triggerFacade);
    timeTriggers.add(tw);
  }
}
