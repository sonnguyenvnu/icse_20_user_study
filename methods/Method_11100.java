/** 
 * @param elapsedTimeRate Elapsed time / Total time
 * @param pow             pow The exponent to use (ex. 3 would return a cubic ease).
 * @return easedValue
 */
private static float getPowOut(float elapsedTimeRate,double pow){
  return (float)((float)1 - Math.pow(1 - elapsedTimeRate,pow));
}
