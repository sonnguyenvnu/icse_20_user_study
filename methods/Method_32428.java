/** 
 * Returns a new instance with the specified number of hours added. <p> This instance is immutable and unaffected by this method call.
 * @param hours  the amount of hours to add, may be negative
 * @return the new period plus the specified number of hours
 * @throws ArithmeticException if the result overflows an int
 */
public Hours plus(int hours){
  if (hours == 0) {
    return this;
  }
  return Hours.hours(FieldUtils.safeAdd(getValue(),hours));
}
