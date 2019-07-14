/** 
 * Instructs the printer to emit a numeric century of era field.
 * @param minDigits  minimum number of digits to print
 * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimatedmaximum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendCenturyOfEra(int minDigits,int maxDigits){
  return appendSignedDecimal(DateTimeFieldType.centuryOfEra(),minDigits,maxDigits);
}
