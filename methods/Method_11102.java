/** 
 * @param elapsedTimeRate Elapsed time / Total time
 * @param amount          amount The strength of the ease.
 * @return easedValue
 */
private static float getBackInOut(float elapsedTimeRate,float amount){
  amount*=1.525;
  if ((elapsedTimeRate*=2) < 1) {
    return (float)(0.5 * (elapsedTimeRate * elapsedTimeRate * ((amount + 1) * elapsedTimeRate - amount)));
  }
  return (float)(0.5 * ((elapsedTimeRate-=2) * elapsedTimeRate * ((amount + 1) * elapsedTimeRate + amount) + 2));
}
