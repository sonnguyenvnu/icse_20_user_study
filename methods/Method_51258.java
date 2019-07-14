private double getStdDev(){
  if (dataPoints.size() < 2) {
    return Double.NaN;
  }
  double mean=getMean();
  double deltaSq=0.0;
  double scoreMinusMean;
  for (  DataPoint point : dataPoints) {
    scoreMinusMean=point.getScore() - mean;
    deltaSq+=scoreMinusMean * scoreMinusMean;
  }
  return Math.sqrt(deltaSq / (dataPoints.size() - 1));
}
