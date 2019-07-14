/** 
 * Creates a new <code>Minutes</code> representing the number of complete standard length minutes in the specified period. <p> This factory method converts all fields from the period to minutes using standardised durations for each field. Only those fields which have a precise duration in the ISO UTC chronology can be converted. <ul> <li>One week consists of 7 days. <li>One day consists of 24 hours. <li>One hour consists of 60 minutes. <li>One minute consists of 60 seconds. <li>One second consists of 1000 milliseconds. </ul> Months and Years are imprecise and periods containing these values cannot be converted.
 * @param period  the period to get the number of minutes from, null returns zero
 * @return the period in minutes
 * @throws IllegalArgumentException if the period contains imprecise duration values
 */
public static Minutes standardMinutesIn(ReadablePeriod period){
  int amount=BaseSingleFieldPeriod.standardPeriodIn(period,DateTimeConstants.MILLIS_PER_MINUTE);
  return Minutes.minutes(amount);
}
