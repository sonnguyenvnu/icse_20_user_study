/** 
 * @param elapsedTimeRate Elapsed time / Total time
 * @return easedValue
 */
private static float getBounceOut(float elapsedTimeRate){
  if (elapsedTimeRate < 1 / 2.75) {
    return (float)(7.5625 * elapsedTimeRate * elapsedTimeRate);
  }
 else   if (elapsedTimeRate < 2 / 2.75) {
    return (float)(7.5625 * (elapsedTimeRate-=1.5 / 2.75) * elapsedTimeRate + 0.75);
  }
 else   if (elapsedTimeRate < 2.5 / 2.75) {
    return (float)(7.5625 * (elapsedTimeRate-=2.25 / 2.75) * elapsedTimeRate + 0.9375);
  }
 else {
    return (float)(7.5625 * (elapsedTimeRate-=2.625 / 2.75) * elapsedTimeRate + 0.984375);
  }
}
