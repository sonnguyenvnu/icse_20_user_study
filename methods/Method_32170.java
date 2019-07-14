/** 
 * Returns a new duration with this length negated. This instance is immutable and is not altered.
 * @return the new duration instance
 */
public Duration negated(){
  if (getMillis() == Long.MIN_VALUE) {
    throw new ArithmeticException("Negation of this duration would overflow");
  }
  return new Duration(-getMillis());
}
