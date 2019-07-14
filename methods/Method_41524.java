/** 
 * <p> Returns the last time at which the <code>SimpleTrigger</code> will fire, before the given time. If the trigger will not fire before the given time, <code>null</code> will be returned. </p>
 */
public Date getFireTimeBefore(Date end){
  if (end.getTime() < getStartTime().getTime()) {
    return null;
  }
  int numFires=computeNumTimesFiredBetween(getStartTime(),end);
  return new Date(getStartTime().getTime() + (numFires * repeatInterval));
}
