/** 
 * Creates a new <code>Months</code> by parsing a string in the ISO8601 format 'PnM'. <p> The parse will accept the full ISO syntax of PnYnMnWnDTnHnMnS however only the months component may be non-zero. If any other component is non-zero, an exception will be thrown.
 * @param periodStr  the period string, null returns zero
 * @return the period in months
 * @throws IllegalArgumentException if the string format is invalid
 */
@FromString public static Months parseMonths(String periodStr){
  if (periodStr == null) {
    return Months.ZERO;
  }
  Period p=PARSER.parsePeriod(periodStr);
  return Months.months(p.getMonths());
}
