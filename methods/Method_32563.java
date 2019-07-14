/** 
 * Get the millis of day property <p> Calling a setter on the property will replace the values of milli-of-second, second-of-minute, minute-of-hour and hour-of-day.
 * @return the millis of day property
 */
public Property millisOfDay(){
  return new Property(this,getChronology().millisOfDay());
}
