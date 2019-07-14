/** 
 * Instructs the printer to emit a field value as a fixed-width decimal number (smaller numbers will be left-padded with zeros), and the parser to expect an unsigned decimal number with the same fixed width.
 * @param fieldType  type of field to append
 * @param numDigits  the exact number of digits to parse or print, except ifprinted value requires more digits
 * @return this DateTimeFormatterBuilder, for chaining
 * @throws IllegalArgumentException if field type is null or if <code>numDigits <= 0</code>
 * @since 1.5
 */
public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType fieldType,int numDigits){
  if (fieldType == null) {
    throw new IllegalArgumentException("Field type must not be null");
  }
  if (numDigits <= 0) {
    throw new IllegalArgumentException("Illegal number of digits: " + numDigits);
  }
  return append0(new FixedNumber(fieldType,numDigits,false));
}
