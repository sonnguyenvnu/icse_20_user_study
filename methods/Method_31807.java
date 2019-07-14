/** 
 * Is this instant strictly before the millisecond instant passed in comparing solely by millisecond.
 * @param instant  a millisecond instant to check against
 * @return true if this instant is strictly before the instant passed in
 */
public boolean isBefore(long instant){
  return (getMillis() < instant);
}
