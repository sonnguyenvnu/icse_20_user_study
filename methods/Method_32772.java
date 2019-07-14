/** 
 * Returns a new instance with the specified number of years added. <p> This instance is immutable and unaffected by this method call.
 * @param years  the amount of years to add, may be negative
 * @return the new period plus the specified number of years
 * @throws ArithmeticException if the result overflows an int
 */
public Years plus(int years){
  if (years == 0) {
    return this;
  }
  return Years.years(FieldUtils.safeAdd(getValue(),years));
}
