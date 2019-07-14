/** 
 * Get the second of minute field value.
 * @return the second of minute
 */
public int getSecondOfMinute(){
  return getChronology().secondOfMinute().get(getMillis());
}
