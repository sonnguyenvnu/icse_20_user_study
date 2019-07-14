/** 
 * Returns a copy of this datetime with the specified date, retaining the time fields. <p> If the date is already the date passed in, then <code>this</code> is returned. <p> To set a single field use the properties, for example: <pre> DateTime set = dt.monthOfYear().setCopy(6); </pre>
 * @param year  the new year value
 * @param monthOfYear  the new monthOfYear value
 * @param dayOfMonth  the new dayOfMonth value
 * @return a copy of this datetime with a different date
 * @throws IllegalArgumentException if any value if invalid
 */
public LocalDateTime withDate(int year,int monthOfYear,int dayOfMonth){
  Chronology chrono=getChronology();
  long instant=getLocalMillis();
  instant=chrono.year().set(instant,year);
  instant=chrono.monthOfYear().set(instant,monthOfYear);
  instant=chrono.dayOfMonth().set(instant,dayOfMonth);
  return withLocalMillis(instant);
}
