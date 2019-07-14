/** 
 * Reset the current state of the identified <code> {@link Trigger}</code> from  {@link TriggerState#ERROR} to {@link TriggerState#NORMAL} or{@link TriggerState#PAUSED} as appropriate.<p>Only affects triggers that are in ERROR state - if identified trigger is not in that state then the result is a no-op.</p> <p>The result will be the trigger returning to the normal, waiting to be fired state, unless the trigger's group has been paused, in which case it will go into the PAUSED state.</p>
 */
public void resetTriggerFromErrorState(final TriggerKey triggerKey) throws JobPersistenceException {
synchronized (lock) {
    TriggerWrapper tw=triggersByKey.get(triggerKey);
    if (tw == null || tw.trigger == null) {
      return;
    }
    if (tw.state != TriggerWrapper.STATE_ERROR) {
      return;
    }
    if (pausedTriggerGroups.contains(triggerKey.getGroup())) {
      tw.state=TriggerWrapper.STATE_PAUSED;
    }
 else {
      tw.state=TriggerWrapper.STATE_WAITING;
      timeTriggers.add(tw);
    }
  }
}
