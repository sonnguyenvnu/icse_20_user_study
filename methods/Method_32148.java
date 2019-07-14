/** 
 * Checks if the given  {@link LocalDateTime} is within a gap.<p> When switching into Daylight Savings Time there is typically a gap where a clock hour is missing. This method identifies whether the local datetime refers to such a gap.
 * @param localDateTime  the time to check, not null
 * @return true if the given datetime refers to a gap
 * @since 1.6
 */
public boolean isLocalDateTimeGap(LocalDateTime localDateTime){
  if (isFixed()) {
    return false;
  }
  try {
    localDateTime.toDateTime(this);
    return false;
  }
 catch (  IllegalInstantException ex) {
    return true;
  }
}
