/** 
 * @param elapsedTimeRate Elapsed time / Total time
 * @param amplitude       Amplitude of easing
 * @param period          Animation of period
 * @return easedValue
 */
private static float getElasticIn(float elapsedTimeRate,double amplitude,double period){
  if (elapsedTimeRate == 0 || elapsedTimeRate == 1)   return elapsedTimeRate;
  double pi2=Math.PI * 2;
  double s=period / pi2 * Math.asin(1 / amplitude);
  return (float)-(amplitude * Math.pow(2f,10f * (elapsedTimeRate-=1f)) * Math.sin((elapsedTimeRate - s) * pi2 / period));
}
