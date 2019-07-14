/** 
 * Sets the number of seconds of the period.
 * @param seconds  the number of seconds
 * @throws IllegalArgumentException if field is not supported and the value is non-zero
 */
public void setSeconds(int seconds){
  super.setField(DurationFieldType.seconds(),seconds);
}
