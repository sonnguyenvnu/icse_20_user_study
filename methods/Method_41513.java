/** 
 * <p> Returns the next time at which the <code>DailyTimeIntervalTrigger</code> will fire, after the given time. If the trigger will not fire after the given time, <code>null</code> will be returned. </p>
 */
@Override public Date getFireTimeAfter(Date afterTime){
  if (complete) {
    return null;
  }
  if (repeatCount != REPEAT_INDEFINITELY && timesTriggered > repeatCount) {
    return null;
  }
  if (afterTime == null) {
    afterTime=new Date(System.currentTimeMillis() + 1000L);
  }
 else {
    afterTime=new Date(afterTime.getTime() + 1000L);
  }
  if (afterTime.before(startTime))   afterTime=startTime;
  boolean afterTimePastEndTimeOfDay=false;
  if (endTimeOfDay != null) {
    afterTimePastEndTimeOfDay=afterTime.getTime() > endTimeOfDay.getTimeOfDayForDate(afterTime).getTime();
  }
  Date fireTime=advanceToNextDayOfWeekIfNecessary(afterTime,afterTimePastEndTimeOfDay);
  if (fireTime == null)   return null;
  Date fireTimeEndDate=null;
  if (endTimeOfDay == null)   fireTimeEndDate=new TimeOfDay(23,59,59).getTimeOfDayForDate(fireTime);
 else   fireTimeEndDate=endTimeOfDay.getTimeOfDayForDate(fireTime);
  Date fireTimeStartDate=startTimeOfDay.getTimeOfDayForDate(fireTime);
  if (fireTime.before(fireTimeStartDate)) {
    return fireTimeStartDate;
  }
  long fireMillis=fireTime.getTime();
  long startMillis=fireTimeStartDate.getTime();
  long secondsAfterStart=(fireMillis - startMillis) / 1000L;
  long repeatLong=getRepeatInterval();
  Calendar sTime=createCalendarTime(fireTimeStartDate);
  IntervalUnit repeatUnit=getRepeatIntervalUnit();
  if (repeatUnit.equals(IntervalUnit.SECOND)) {
    long jumpCount=secondsAfterStart / repeatLong;
    if (secondsAfterStart % repeatLong != 0)     jumpCount++;
    sTime.add(Calendar.SECOND,getRepeatInterval() * (int)jumpCount);
    fireTime=sTime.getTime();
  }
 else   if (repeatUnit.equals(IntervalUnit.MINUTE)) {
    long jumpCount=secondsAfterStart / (repeatLong * 60L);
    if (secondsAfterStart % (repeatLong * 60L) != 0)     jumpCount++;
    sTime.add(Calendar.MINUTE,getRepeatInterval() * (int)jumpCount);
    fireTime=sTime.getTime();
  }
 else   if (repeatUnit.equals(IntervalUnit.HOUR)) {
    long jumpCount=secondsAfterStart / (repeatLong * 60L * 60L);
    if (secondsAfterStart % (repeatLong * 60L * 60L) != 0)     jumpCount++;
    sTime.add(Calendar.HOUR_OF_DAY,getRepeatInterval() * (int)jumpCount);
    fireTime=sTime.getTime();
  }
  if (fireTime.after(fireTimeEndDate)) {
    fireTime=advanceToNextDayOfWeekIfNecessary(fireTime,isSameDay(fireTime,fireTimeEndDate));
    fireTime=startTimeOfDay.getTimeOfDayForDate(fireTime);
  }
  return fireTime;
}
