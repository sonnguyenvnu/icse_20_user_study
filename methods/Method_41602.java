/** 
 * <p> Retrieve the given <code> {@link org.quartz.Trigger}</code>. </p>
 * @return The desired <code>Trigger</code>, or null if there is nomatch.
 */
public OperableTrigger retrieveTrigger(TriggerKey triggerKey){
synchronized (lock) {
    TriggerWrapper tw=triggersByKey.get(triggerKey);
    return (tw != null) ? (OperableTrigger)tw.getTrigger().clone() : null;
  }
}
