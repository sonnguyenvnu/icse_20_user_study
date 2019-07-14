/** 
 * <p> Returns the final time at which the <code>DailyTimeIntervalTrigger</code> will fire, if there is no end time set, null will be returned. </p> <p> Note that the return time may be in the past. </p>
 */
@Override public Date getFinalFireTime(){
  if (complete || getEndTime() == null) {
    return null;
  }
  Date eTime=getEndTime();
  if (endTimeOfDay != null) {
    Date endTimeOfDayDate=endTimeOfDay.getTimeOfDayForDate(eTime);
    if (eTime.getTime() < endTimeOfDayDate.getTime()) {
      eTime=endTimeOfDayDate;
    }
  }
  return eTime;
}
