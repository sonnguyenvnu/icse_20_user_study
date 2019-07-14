/** 
 * <p> Pause the <code> {@link Trigger}</code> with the given name. </p>
 */
public void pauseTrigger(TriggerKey triggerKey){
synchronized (lock) {
    TriggerWrapper tw=triggersByKey.get(triggerKey);
    if (tw == null || tw.trigger == null) {
      return;
    }
    if (tw.state == TriggerWrapper.STATE_COMPLETE) {
      return;
    }
    if (tw.state == TriggerWrapper.STATE_BLOCKED) {
      tw.state=TriggerWrapper.STATE_PAUSED_BLOCKED;
    }
 else {
      tw.state=TriggerWrapper.STATE_PAUSED;
    }
    timeTriggers.remove(tw);
  }
}
