/** 
 * Adds the specified millis to the number of millis in the period.
 * @param millis  the number of millis
 * @throws IllegalArgumentException if field is not supported and the value is non-zero
 * @throws ArithmeticException if the addition exceeds the capacity of the period
 */
public void addMillis(int millis){
  super.addField(DurationFieldType.millis(),millis);
}
