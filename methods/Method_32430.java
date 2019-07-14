/** 
 * Returns a new instance with the hours value negated.
 * @return the new period with a negated value
 * @throws ArithmeticException if the result overflows an int
 */
public Hours negated(){
  return Hours.hours(FieldUtils.safeNegate(getValue()));
}
