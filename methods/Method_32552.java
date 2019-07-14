/** 
 * Sets the millisecond instant of this instant from another. <p> This method does not change the chronology of this instant, just the millisecond instant.
 * @param instant  the instant to use, null means now
 */
public void setMillis(ReadableInstant instant){
  long instantMillis=DateTimeUtils.getInstantMillis(instant);
  setMillis(instantMillis);
}
