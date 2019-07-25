/** 
 * Calculate pearson's R for the corellation between confidence and correct, where 1 = correct and -1 = incorrect
 */
public double correlation(){
  double xSum=0;
  double xSumOfSquares=0;
  double ySum=0;
  double ySumOfSquares=0;
  double xySum=0;
  for (int i=0; i < size(); i++) {
    double value=((EntityConfidence)confidences.get(i)).correct() ? 1.0 : -1.0;
    xSum+=value;
    xSumOfSquares+=(value * value);
    double conf=((EntityConfidence)confidences.get(i)).confidence();
    ySum+=conf;
    ySumOfSquares+=(conf * conf);
    xySum+=value * conf;
  }
  double xVariance=xSumOfSquares - (xSum * xSum / size());
  double yVariance=ySumOfSquares - (ySum * ySum / size());
  double crossVariance=xySum - (xSum * ySum / size());
  return crossVariance / Math.sqrt(xVariance * yVariance);
}
