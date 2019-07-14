/** 
 * Creates a new instance representing the number of complete standard length units in the specified period. <p> This factory method converts all fields from the period to hours using standardised durations for each field. Only those fields which have a precise duration in the ISO UTC chronology can be converted. <ul> <li>One week consists of 7 days. <li>One day consists of 24 hours. <li>One hour consists of 60 minutes. <li>One minute consists of 60 seconds. <li>One second consists of 1000 milliseconds. </ul> Months and Years are imprecise and periods containing these values cannot be converted.
 * @param period  the period to get the number of hours from, must not be null
 * @param millisPerUnit  the number of milliseconds in one standard unit of this period
 * @throws IllegalArgumentException if the period contains imprecise duration values
 */
protected static int standardPeriodIn(ReadablePeriod period,long millisPerUnit){
  if (period == null) {
    return 0;
  }
  Chronology iso=ISOChronology.getInstanceUTC();
  long duration=0L;
  for (int i=0; i < period.size(); i++) {
    int value=period.getValue(i);
    if (value != 0) {
      DurationField field=period.getFieldType(i).getField(iso);
      if (field.isPrecise() == false) {
        throw new IllegalArgumentException("Cannot convert period to duration as " + field.getName() + " is not precise in the period " + period);
      }
      duration=FieldUtils.safeAdd(duration,FieldUtils.safeMultiply(field.getUnitMillis(),value));
    }
  }
  return FieldUtils.safeToInt(duration / millisPerUnit);
}
