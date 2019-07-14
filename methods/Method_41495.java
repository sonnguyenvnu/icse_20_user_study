/** 
 * <p> Returns the final time at which the <code>DateIntervalTrigger</code> will fire, if there is no end time set, null will be returned. </p> <p> Note that the return time may be in the past. </p>
 */
@Override public Date getFinalFireTime(){
  if (complete || getEndTime() == null) {
    return null;
  }
  Date fTime=new Date(getEndTime().getTime() - 1000L);
  fTime=getFireTimeAfter(fTime,true);
  if (fTime.equals(getEndTime()))   return fTime;
  Calendar lTime=Calendar.getInstance();
  if (timeZone != null)   lTime.setTimeZone(timeZone);
  lTime.setTime(fTime);
  lTime.setLenient(true);
  if (getRepeatIntervalUnit().equals(IntervalUnit.SECOND)) {
    lTime.add(java.util.Calendar.SECOND,-1 * getRepeatInterval());
  }
 else   if (getRepeatIntervalUnit().equals(IntervalUnit.MINUTE)) {
    lTime.add(java.util.Calendar.MINUTE,-1 * getRepeatInterval());
  }
 else   if (getRepeatIntervalUnit().equals(IntervalUnit.HOUR)) {
    lTime.add(java.util.Calendar.HOUR_OF_DAY,-1 * getRepeatInterval());
  }
 else   if (getRepeatIntervalUnit().equals(IntervalUnit.DAY)) {
    lTime.add(java.util.Calendar.DAY_OF_YEAR,-1 * getRepeatInterval());
  }
 else   if (getRepeatIntervalUnit().equals(IntervalUnit.WEEK)) {
    lTime.add(java.util.Calendar.WEEK_OF_YEAR,-1 * getRepeatInterval());
  }
 else   if (getRepeatIntervalUnit().equals(IntervalUnit.MONTH)) {
    lTime.add(java.util.Calendar.MONTH,-1 * getRepeatInterval());
  }
 else   if (getRepeatIntervalUnit().equals(IntervalUnit.YEAR)) {
    lTime.add(java.util.Calendar.YEAR,-1 * getRepeatInterval());
  }
  return lTime.getTime();
}
