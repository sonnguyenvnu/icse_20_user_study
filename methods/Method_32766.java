/** 
 * Converts this object to a DateMidnight.
 * @param zone  the zone to get the DateMidnight in, null means default
 * @return the DateMidnight instance
 */
public DateMidnight toDateMidnight(DateTimeZone zone){
  Chronology chrono=getChronology().withZone(zone);
  return new DateMidnight(getYear(),getMonthOfYear(),getDayOfMonth(),chrono);
}
