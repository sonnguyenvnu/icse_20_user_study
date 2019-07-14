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
  if (obj instanceof GJChronology) {
    GJChronology chrono=(GJChronology)obj;
    return iCutoverMillis == chrono.iCutoverMillis && getMinimumDaysInFirstWeek() == chrono.getMinimumDaysInFirstWeek() && getZone().equals(chrono.getZone());
  }
  return false;
}
