/** 
 * <p> Redefine a certain day of the month to be excluded (true) or included (false). </p>
 * @param day The day of the month (from 1 to 31) to set.
 */
public void setDayExcluded(int day,boolean exclude){
  if ((day < 1) || (day > MAX_DAYS_IN_MONTH)) {
    throw new IllegalArgumentException("The day parameter must be in the range of 1 to " + MAX_DAYS_IN_MONTH);
  }
  excludeDays[day - 1]=exclude;
  excludeAll=areAllDaysExcluded();
}
