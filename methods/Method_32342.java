/** 
 * Instructs the printer to emit a numeric clockhourOfDay field.
 * @param minDigits minimum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendClockhourOfDay(int minDigits){
  return appendDecimal(DateTimeFieldType.clockhourOfDay(),minDigits,2);
}
