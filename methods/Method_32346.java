/** 
 * Instructs the printer to emit a numeric weekyear field.
 * @param minDigits  minimum number of digits to <i>print</i>
 * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimatedmaximum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendWeekyear(int minDigits,int maxDigits){
  return appendSignedDecimal(DateTimeFieldType.weekyear(),minDigits,maxDigits);
}
