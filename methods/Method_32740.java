/** 
 * Returns a new instance with the weeks value negated.
 * @return the new period with a negated value
 * @throws ArithmeticException if the result overflows an int
 */
public Weeks negated(){
  return Weeks.weeks(FieldUtils.safeNegate(getValue()));
}
