/** 
 * Instructs the printer to emit a numeric millisOfDay field.
 * @param minDigits  minimum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendMillisOfDay(int minDigits){
  return appendDecimal(DateTimeFieldType.millisOfDay(),minDigits,8);
}
