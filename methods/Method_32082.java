/** 
 * Rounds the specified instant to midnight.
 * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
 * @param chronology  the chronology to use, not null
 * @return the updated instant, rounded to midnight
 */
protected long checkInstant(long instant,Chronology chronology){
  return chronology.dayOfMonth().roundFloor(instant);
}
