/** 
 * Returns the fractional duration milliseconds of this field. In other words, calling remainder returns the duration that roundFloor would subtract. <p> For example, on a datetime of 2002-11-02T23:34:56.789, the remainder by hour is 34 minutes and 56.789 seconds. <p> The default implementation computes <code>instant - roundFloor(instant)</code>. Subclasses are encouraged to provide a more efficient implementation.
 * @param instant the milliseconds from 1970-01-01T00:00:00Z to get theremainder
 * @return remainder duration, in milliseconds
 */
public long remainder(long instant){
  return instant - roundFloor(instant);
}
