/** 
 * Output the time in ISO8601 format (HH:mm:ss.SSS).
 * @return ISO8601 time formatted string.
 */
@ToString public String toString(){
  return ISODateTimeFormat.time().print(this);
}
