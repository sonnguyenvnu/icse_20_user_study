/** 
 * Checks if this chronology instance equals another.
 * @param obj  the object to compare to
 * @return true if equal
 * @since 1.6
 */
public boolean equals(Object obj){
  if (this == obj) {
    return true;
  }
  if (obj != null && getClass() == obj.getClass()) {
    BasicChronology chrono=(BasicChronology)obj;
    return getMinimumDaysInFirstWeek() == chrono.getMinimumDaysInFirstWeek() && getZone().equals(chrono.getZone());
  }
  return false;
}
