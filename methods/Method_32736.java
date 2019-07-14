/** 
 * Returns a new instance with the specified number of weeks added. <p> This instance is immutable and unaffected by this method call.
 * @param weeks  the amount of weeks to add, may be negative
 * @return the new period plus the specified number of weeks
 * @throws ArithmeticException if the result overflows an int
 */
public Weeks plus(int weeks){
  if (weeks == 0) {
    return this;
  }
  return Weeks.weeks(FieldUtils.safeAdd(getValue(),weeks));
}
