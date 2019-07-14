/** 
 * <p> Inform the <code>JobStore</code> that the scheduler no longer plans to fire the given <code>Trigger</code>, that it had previously acquired (reserved). </p>
 */
public void releaseAcquiredTrigger(OperableTrigger trigger){
synchronized (lock) {
    TriggerWrapper tw=triggersByKey.get(trigger.getKey());
    if (tw != null && tw.state == TriggerWrapper.STATE_ACQUIRED) {
      tw.state=TriggerWrapper.STATE_WAITING;
      timeTriggers.add(tw);
    }
  }
}
