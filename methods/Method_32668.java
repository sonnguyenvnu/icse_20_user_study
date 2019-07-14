/** 
 * Returns a new instance with the specified number of seconds added. <p> This instance is immutable and unaffected by this method call.
 * @param seconds  the amount of seconds to add, may be negative
 * @return the new period plus the specified number of seconds
 * @throws ArithmeticException if the result overflows an int
 */
public Seconds plus(int seconds){
  if (seconds == 0) {
    return this;
  }
  return Seconds.seconds(FieldUtils.safeAdd(getValue(),seconds));
}
