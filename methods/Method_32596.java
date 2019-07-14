/** 
 * Sets the number of millis of the period.
 * @param millis  the number of millis
 * @throws IllegalArgumentException if field is not supported and the value is non-zero
 */
public void setMillis(int millis){
  super.setField(DurationFieldType.millis(),millis);
}
