/** 
 * Returns a new instance with the years multiplied by the specified scalar. <p> This instance is immutable and unaffected by this method call.
 * @param scalar  the amount to multiply by, may be negative
 * @return the new period multiplied by the specified scalar
 * @throws ArithmeticException if the result overflows an int
 */
public Years multipliedBy(int scalar){
  return Years.years(FieldUtils.safeMultiply(getValue(),scalar));
}
