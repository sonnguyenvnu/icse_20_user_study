/** 
 * Instructs the printer to emit a numeric year field.
 * @param minDigits  minimum number of digits to <i>print</i>
 * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimatedmaximum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendYear(int minDigits,int maxDigits){
  return appendSignedDecimal(DateTimeFieldType.year(),minDigits,maxDigits);
}
