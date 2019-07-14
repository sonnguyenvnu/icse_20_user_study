public TriggerKey removeFirst(){
  Iterator<TimeTrigger> iter=timeTriggers.iterator();
  TimeTrigger tt=null;
  if (iter.hasNext()) {
    tt=iter.next();
    iter.remove();
  }
  return tt == null ? null : tt.getTriggerKey();
}
