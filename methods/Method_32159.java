/** 
 * Returns a new instance with the days multiplied by the specified scalar. <p> This instance is immutable and unaffected by this method call.
 * @param scalar  the amount to multiply by, may be negative
 * @return the new period multiplied by the specified scalar
 * @throws ArithmeticException if the result overflows an int
 */
public Days multipliedBy(int scalar){
  return Days.days(FieldUtils.safeMultiply(getValue(),scalar));
}
