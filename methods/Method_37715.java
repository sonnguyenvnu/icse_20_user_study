/** 
 * Converts to milliseconds.
 */
public long toMilliseconds(){
  double then=(fraction - JD_1970.fraction) * MILLIS_IN_DAY;
  then+=(integer - JD_1970.integer) * MILLIS_IN_DAY;
  then+=then > 0 ? 1.0e-6 : -1.0e-6;
  return (long)then;
}
