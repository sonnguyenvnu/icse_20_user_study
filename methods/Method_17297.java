/** 
 * @return the current time in milliseconds 
 */
protected final long currentTimeMillis(){
  return nanosToMillis(ticker.read());
}
