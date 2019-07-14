/** 
 * <p> Returns the final time at which the <code>SimpleTrigger</code> will fire, if repeatCount is REPEAT_INDEFINITELY, null will be returned. </p> <p> Note that the return time may be in the past. </p>
 */
@Override public Date getFinalFireTime(){
  if (repeatCount == 0) {
    return startTime;
  }
  if (repeatCount == REPEAT_INDEFINITELY) {
    return (getEndTime() == null) ? null : getFireTimeBefore(getEndTime());
  }
  long lastTrigger=startTime.getTime() + (repeatCount * repeatInterval);
  if ((getEndTime() == null) || (lastTrigger < getEndTime().getTime())) {
    return new Date(lastTrigger);
  }
 else {
    return getFireTimeBefore(getEndTime());
  }
}
