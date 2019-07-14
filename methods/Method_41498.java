/** 
 * <p> Returns the next time at which the <code>CronTrigger</code> will fire, after the given time. If the trigger will not fire after the given time, <code>null</code> will be returned. </p> <p> Note that the date returned is NOT validated against the related org.quartz.Calendar (if any) </p>
 */
@Override public Date getFireTimeAfter(Date afterTime){
  if (afterTime == null) {
    afterTime=new Date();
  }
  if (getStartTime().after(afterTime)) {
    afterTime=new Date(getStartTime().getTime() - 1000l);
  }
  if (getEndTime() != null && (afterTime.compareTo(getEndTime()) >= 0)) {
    return null;
  }
  Date pot=getTimeAfter(afterTime);
  if (getEndTime() != null && pot != null && pot.after(getEndTime())) {
    return null;
  }
  return pot;
}
