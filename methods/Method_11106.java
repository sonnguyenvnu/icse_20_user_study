/** 
 * @param elapsedTimeRate Elapsed time / Total time
 * @param amplitude       Amplitude of easing
 * @param period          Animation of period
 * @return easedValue
 */
private static float getElasticInOut(float elapsedTimeRate,double amplitude,double period){
  double pi2=Math.PI * 2;
  double s=period / pi2 * Math.asin(1 / amplitude);
  if ((elapsedTimeRate*=2) < 1) {
    return (float)(-0.5f * (amplitude * Math.pow(2,10 * (elapsedTimeRate-=1f)) * Math.sin((elapsedTimeRate - s) * pi2 / period)));
  }
  return (float)(amplitude * Math.pow(2,-10 * (elapsedTimeRate-=1)) * Math.sin((elapsedTimeRate - s) * pi2 / period) * 0.5 + 1);
}
