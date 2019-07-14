@Override protected double adjust(double hitRate){
  if (hitRate < (previousHitRate + tolerance)) {
    increaseWindow=!increaseWindow;
  }
  if (Math.abs(hitRate - previousHitRate) >= restartThreshold) {
    sampleSize=initialSampleSize;
    stepSize=initialStepSize;
  }
  return increaseWindow ? stepSize : -stepSize;
}
