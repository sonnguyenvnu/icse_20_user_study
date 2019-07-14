/** 
 * <p> Add the given Date to the list of excluded days. Only the month, day and year of the returned dates are significant. </p>
 */
public void addExcludedDate(Date excludedDate){
  Date date=getStartOfDayJavaCalendar(excludedDate.getTime()).getTime();
  this.dates.add(date);
}
