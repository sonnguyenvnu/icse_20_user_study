/** 
 * Instructs the printer to emit a numeric clockhourOfHalfday field.
 * @param minDigits  minimum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendClockhourOfHalfday(int minDigits){
  return appendDecimal(DateTimeFieldType.clockhourOfHalfday(),minDigits,2);
}
