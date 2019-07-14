/** 
 * Instructs the printer to emit a numeric weekOfWeekyear field.
 * @param minDigits  minimum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendWeekOfWeekyear(int minDigits){
  return appendDecimal(DateTimeFieldType.weekOfWeekyear(),minDigits,2);
}
