/** 
 * Round to the nearest whole unit of this field. If the given millisecond value is closer to the floor, this function behaves like roundFloor. If the millisecond value is closer to the ceiling or is exactly halfway, this function behaves like roundCeiling.
 * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
 * @return rounded milliseconds
 */
public long roundHalfCeiling(long instant){
  long floor=roundFloor(instant);
  long ceiling=roundCeiling(instant);
  long diffFromFloor=instant - floor;
  long diffToCeiling=ceiling - instant;
  if (diffToCeiling <= diffFromFloor) {
    return ceiling;
  }
 else {
    return floor;
  }
}
