/** 
 * Is this instant equal to the millisecond instant passed in comparing solely by millisecond.
 * @param instant  a millisecond instant to check against
 * @return true if this instant is equal to the instant passed in
 */
public boolean isEqual(long instant){
  return (getMillis() == instant);
}
