/** 
 * Returns mapping of any input degrees (0 to 360) to one of 12 visible output degrees (all multiples of 30), where the input will be "snapped" to the closest visible degrees.
 * @param degrees The input degrees
 * @param forceAboveOrBelow The output may be forced to either the higher or lower step, or maybe allowed to snap to whichever is closer. Use 1 to force strictly higher, -1 to force strictly lower, and 0 to snap to the closer one.
 * @return output degrees, will be a multiple of 30
 */
private static int snapOnly30s(int degrees,int forceHigherOrLower){
  int stepSize=HOUR_VALUE_TO_DEGREES_STEP_SIZE;
  int floor=(degrees / stepSize) * stepSize;
  int ceiling=floor + stepSize;
  if (forceHigherOrLower == 1) {
    degrees=ceiling;
  }
 else   if (forceHigherOrLower == -1) {
    if (degrees == floor) {
      floor-=stepSize;
    }
    degrees=floor;
  }
 else {
    if ((degrees - floor) < (ceiling - degrees)) {
      degrees=floor;
    }
 else {
      degrees=ceiling;
    }
  }
  return degrees;
}
