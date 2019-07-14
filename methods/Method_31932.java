/** 
 * Set the Year of a week based year component of the specified time instant.
 * @see org.joda.time.DateTimeField#set
 * @param instant  the time instant in millis to update.
 * @param year  the year (-9999,9999) to set the date to.
 * @return the updated DateTime.
 * @throws IllegalArgumentException  if year is invalid.
 */
public long set(long instant,int year){
  FieldUtils.verifyValueBounds(this,Math.abs(year),iChronology.getMinYear(),iChronology.getMaxYear());
  int thisWeekyear=get(instant);
  if (thisWeekyear == year) {
    return instant;
  }
  int thisDow=iChronology.getDayOfWeek(instant);
  int weeksInFromYear=iChronology.getWeeksInYear(thisWeekyear);
  int weeksInToYear=iChronology.getWeeksInYear(year);
  int maxOutWeeks=(weeksInToYear < weeksInFromYear) ? weeksInToYear : weeksInFromYear;
  int setToWeek=iChronology.getWeekOfWeekyear(instant);
  if (setToWeek > maxOutWeeks) {
    setToWeek=maxOutWeeks;
  }
  long workInstant=instant;
  workInstant=iChronology.setYear(workInstant,year);
  int workWoyYear=get(workInstant);
  if (workWoyYear < year) {
    workInstant+=DateTimeConstants.MILLIS_PER_WEEK;
  }
 else   if (workWoyYear > year) {
    workInstant-=DateTimeConstants.MILLIS_PER_WEEK;
  }
  int currentWoyWeek=iChronology.getWeekOfWeekyear(workInstant);
  workInstant=workInstant + (setToWeek - currentWoyWeek) * (long)DateTimeConstants.MILLIS_PER_WEEK;
  workInstant=iChronology.dayOfWeek().set(workInstant,thisDow);
  return workInstant;
}
