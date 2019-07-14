/** 
 * Sets all the fields in one go from a millisecond interval using ISOChronology and dividing the fields using the period type.
 * @param startInstant  interval start, in milliseconds
 * @param endInstant  interval end, in milliseconds
 * @throws ArithmeticException if the set exceeds the capacity of the period
 */
public void setPeriod(long startInstant,long endInstant){
  setPeriod(startInstant,endInstant,null);
}
