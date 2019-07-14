/** 
 * Is this instant equal to the current instant comparing solely by millisecond.
 * @return true if this instant is equal to the current instant
 */
public boolean isEqualNow(){
  return isEqual(DateTimeUtils.currentTimeMillis());
}
