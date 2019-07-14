/** 
 * Checks whether the partial is contiguous. <p> A partial is contiguous if one field starts where another ends. <p> For example <code>LocalDate</code> is contiguous because DayOfMonth has the same range (Month) as the unit of the next field (MonthOfYear), and MonthOfYear has the same range (Year) as the unit of the next field (Year). <p> Similarly, <code>LocalTime</code> is contiguous, as it consists of MillisOfSecond, SecondOfMinute, MinuteOfHour and HourOfDay (note how the names of each field 'join up'). <p> However, a Year/HourOfDay partial is not contiguous because the range field Day is not equal to the next field Year. Similarly, a DayOfWeek/DayOfMonth partial is not contiguous because the range Month is not equal to the next field Day.
 * @param partial  the partial to check
 * @return true if the partial is contiguous
 * @throws IllegalArgumentException if the partial is null
 * @since 1.1
 */
public static final boolean isContiguous(ReadablePartial partial){
  if (partial == null) {
    throw new IllegalArgumentException("Partial must not be null");
  }
  DurationFieldType lastType=null;
  for (int i=0; i < partial.size(); i++) {
    DateTimeField loopField=partial.getField(i);
    if (i > 0) {
      if (loopField.getRangeDurationField() == null || loopField.getRangeDurationField().getType() != lastType) {
        return false;
      }
    }
    lastType=loopField.getDurationField().getType();
  }
  return true;
}
