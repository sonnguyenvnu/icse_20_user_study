/** 
 * Given fireTime time determine if it is on a valid day of week. If so, simply return it unaltered, if not, advance to the next valid week day, and set the time of day to the start time of day
 * @param fireTime - given next fireTime.
 * @param forceToAdvanceNextDay - flag to whether to advance day without check existing week day. This scenariocan happen when a caller determine fireTime has passed the endTimeOfDay that fireTime should move to next day anyway.
 * @return a next day fireTime.
 */
private Date advanceToNextDayOfWeekIfNecessary(Date fireTime,boolean forceToAdvanceNextDay){
  TimeOfDay sTimeOfDay=getStartTimeOfDay();
  Date fireTimeStartDate=sTimeOfDay.getTimeOfDayForDate(fireTime);
  Calendar fireTimeStartDateCal=createCalendarTime(fireTimeStartDate);
  int dayOfWeekOfFireTime=fireTimeStartDateCal.get(Calendar.DAY_OF_WEEK);
  Set<Integer> daysOfWeekToFire=getDaysOfWeek();
  if (forceToAdvanceNextDay || !daysOfWeekToFire.contains(dayOfWeekOfFireTime)) {
    for (int i=1; i <= 7; i++) {
      fireTimeStartDateCal.add(Calendar.DATE,1);
      dayOfWeekOfFireTime=fireTimeStartDateCal.get(Calendar.DAY_OF_WEEK);
      if (daysOfWeekToFire.contains(dayOfWeekOfFireTime)) {
        fireTime=fireTimeStartDateCal.getTime();
        break;
      }
    }
  }
  Date eTime=getEndTime();
  if (eTime != null && fireTime.getTime() > eTime.getTime()) {
    return null;
  }
  return fireTime;
}
