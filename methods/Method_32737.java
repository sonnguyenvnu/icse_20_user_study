/** 
 * Returns a new instance with the specified number of weeks taken away. <p> This instance is immutable and unaffected by this method call.
 * @param weeks  the amount of weeks to take away, may be negative
 * @return the new period minus the specified number of weeks
 * @throws ArithmeticException if the result overflows an int
 */
public Weeks minus(int weeks){
  return plus(FieldUtils.safeNegate(weeks));
}
