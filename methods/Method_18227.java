/** 
 * Record an  {@link EventTrigger} according to its key.
 * @param trigger
 */
public void recordEventTrigger(@Nullable EventTrigger trigger){
  if (trigger == null) {
    return;
  }
synchronized (this) {
    if (mEventTriggers == null) {
      mEventTriggers=new HashMap<>();
    }
    mEventTriggers.put(trigger.mKey,trigger);
  }
}
