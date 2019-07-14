/** 
 * Creates a <code>Weeks</code> representing the number of whole weeks between the two specified datetimes.
 * @param start  the start instant, must not be null
 * @param end  the end instant, must not be null
 * @return the period in weeks
 * @throws IllegalArgumentException if the instants are null or invalid
 */
public static Weeks weeksBetween(ReadableInstant start,ReadableInstant end){
  int amount=BaseSingleFieldPeriod.between(start,end,DurationFieldType.weeks());
  return Weeks.weeks(amount);
}
