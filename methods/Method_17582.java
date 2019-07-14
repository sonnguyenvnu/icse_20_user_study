@Override protected double adjust(double hitRate){
  if ((previousHitRate - hitRate) >= restartTolerance) {
    restart();
  }
  if (temperature <= minTemperature) {
    return 0.0;
  }
  double criteria=random.nextGaussian();
  double acceptanceProbability=Math.exp((hitRate - previousHitRate) / (100 * temperature));
  if ((hitRate < previousHitRate) && (acceptanceProbability <= criteria)) {
    increaseWindow=!increaseWindow;
    stepSize=Math.max(stepSize - 1,0);
  }
  if ((previousHitRate - hitRate) > coolDownTolerance) {
    temperature=coolDownRate * temperature;
    stepSize=1 + (int)(stepSize * temperature);
  }
  return increaseWindow ? stepSize : -stepSize;
}
