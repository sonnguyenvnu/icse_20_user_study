/** 
 * @param elapsedTimeRate Elapsed time / Total time
 * @param pow             pow The exponent to use (ex. 3 would return a cubic ease).
 * @return easedValue
 */
private static float getPowIn(float elapsedTimeRate,double pow){
  return (float)Math.pow(elapsedTimeRate,pow);
}
