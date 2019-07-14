/** 
 * Instructs the printer to emit a field value as a decimal number, and the parser to expect an unsigned decimal number.
 * @param fieldType  type of field to append
 * @param minDigits  minimum number of digits to <i>print</i>
 * @param maxDigits  maximum number of digits to <i>parse</i>, or the estimatedmaximum number of digits to print
 * @return this DateTimeFormatterBuilder, for chaining
 * @throws IllegalArgumentException if field type is null
 */
public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType fieldType,int minDigits,int maxDigits){
  if (fieldType == null) {
    throw new IllegalArgumentException("Field type must not be null");
  }
  if (maxDigits < minDigits) {
    maxDigits=minDigits;
  }
  if (minDigits < 0 || maxDigits <= 0) {
    throw new IllegalArgumentException();
  }
  if (minDigits <= 1) {
    return append0(new UnpaddedNumber(fieldType,maxDigits,false));
  }
 else {
    return append0(new PaddedNumber(fieldType,maxDigits,false,minDigits));
  }
}
