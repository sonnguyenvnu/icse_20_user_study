/** 
 * Instructs the printer to emit a numeric millisOfSecond field. <p> This method will append a field that prints a three digit value. During parsing the value that is parsed is assumed to be three digits. If less than three digits are present then they will be counted as the smallest parts of the millisecond. This is probably not what you want if you are using the field as a fraction. Instead, a fractional millisecond should be produced using  {@link #appendFractionOfSecond}.
 * @param minDigits  minimum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendMillisOfSecond(int minDigits){
  return appendDecimal(DateTimeFieldType.millisOfSecond(),minDigits,3);
}
