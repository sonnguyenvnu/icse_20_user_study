/** 
 * Round to the nearest whole unit of this field. If the given millisecond value is closer to the floor, this function behaves like roundFloor. If the millisecond value is closer to the ceiling, this function behaves like roundCeiling. <p> If the millisecond value is exactly halfway between the floor and ceiling, the ceiling is chosen over the floor only if it makes this field's value even.
 * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
 * @return rounded milliseconds
 */
public long roundHalfEven(long instant){
  long floor=roundFloor(instant);
  long ceiling=roundCeiling(instant);
  long diffFromFloor=instant - floor;
  long diffToCeiling=ceiling - instant;
  if (diffFromFloor < diffToCeiling) {
    return floor;
  }
 else   if (diffToCeiling < diffFromFloor) {
    return ceiling;
  }
 else {
    if ((get(ceiling) & 1) == 0) {
      return ceiling;
    }
    return floor;
  }
}
