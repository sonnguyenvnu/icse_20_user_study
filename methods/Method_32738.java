/** 
 * Returns a new instance with the weeks multiplied by the specified scalar. <p> This instance is immutable and unaffected by this method call.
 * @param scalar  the amount to multiply by, may be negative
 * @return the new period multiplied by the specified scalar
 * @throws ArithmeticException if the result overflows an int
 */
public Weeks multipliedBy(int scalar){
  return Weeks.weeks(FieldUtils.safeMultiply(getValue(),scalar));
}
