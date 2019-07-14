/** 
 * @param elapsedTimeRate Elapsed time / Total time
 * @param pow             pow The exponent to use (ex. 3 would return a cubic ease).
 * @return easedValue
 */
private static float getPowInOut(float elapsedTimeRate,double pow){
  if ((elapsedTimeRate*=2) < 1) {
    return (float)(0.5 * Math.pow(elapsedTimeRate,pow));
  }
  return (float)(1 - 0.5 * Math.abs(Math.pow(2 - elapsedTimeRate,pow)));
}
