/** 
 * Get this object as an Instant.
 * @return an Instant using the same millis
 */
public Instant toInstant(){
  return new Instant(getMillis());
}
