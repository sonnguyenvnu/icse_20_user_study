/** 
 * Is this instant strictly after the millisecond instant passed in comparing solely by millisecond.
 * @param instant  a millisecond instant to check against
 * @return true if this instant is strictly after the instant passed in
 */
public boolean isAfter(long instant){
  return (getMillis() > instant);
}
