/** 
 * Adds the specified seconds to the number of seconds in the period.
 * @param seconds  the number of seconds
 * @throws IllegalArgumentException if field is not supported and the value is non-zero
 * @throws ArithmeticException if the addition exceeds the capacity of the period
 */
public void addSeconds(int seconds){
  super.addField(DurationFieldType.seconds(),seconds);
}
