/** 
 * Round to the highest whole unit of this field. The value of this field and all fields of a higher magnitude may be incremented in order to achieve this result. The fractional millis that cannot be expressed in whole increments of this field are set to minimum. <p> For example, a datetime of 2002-11-02T23:34:56.789, rounded to the highest whole hour is 2002-11-03T00:00:00.000. <p> The default implementation calls roundFloor, and if the instant is modified as a result, adds one field unit. Subclasses are encouraged to provide a more efficient implementation.
 * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
 * @return rounded milliseconds
 */
public long roundCeiling(long instant){
  long newInstant=roundFloor(instant);
  if (newInstant != instant) {
    instant=add(newInstant,1);
  }
  return instant;
}
