/** 
 * Instructs the printer to emit a numeric hourOfHalfday field.
 * @param minDigits  minimum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendHourOfHalfday(int minDigits){
  return appendDecimal(DateTimeFieldType.hourOfHalfday(),minDigits,2);
}
