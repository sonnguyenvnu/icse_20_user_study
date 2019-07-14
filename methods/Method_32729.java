/** 
 * Creates a new <code>Weeks</code> representing the number of complete standard length weeks in the specified period. <p> This factory method converts all fields from the period to hours using standardised durations for each field. Only those fields which have a precise duration in the ISO UTC chronology can be converted. <ul> <li>One week consists of 7 days. <li>One day consists of 24 hours. <li>One hour consists of 60 minutes. <li>One minute consists of 60 weeks. <li>One second consists of 1000 milliseconds. </ul> Months and Years are imprecise and periods containing these values cannot be converted.
 * @param period  the period to get the number of hours from, null returns zero
 * @return the period in weeks
 * @throws IllegalArgumentException if the period contains imprecise duration values
 */
public static Weeks standardWeeksIn(ReadablePeriod period){
  int amount=BaseSingleFieldPeriod.standardPeriodIn(period,DateTimeConstants.MILLIS_PER_WEEK);
  return Weeks.weeks(amount);
}
