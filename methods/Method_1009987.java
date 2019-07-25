@Override public double quantile(double q){
  if (q < 0 || q > 1) {
    throw new IllegalArgumentException("q should be in [0,1], got " + q);
  }
  mergeNewValues();
  if (lastUsedCell == 0) {
    return Double.NaN;
  }
 else   if (lastUsedCell == 1) {
    return mean[0];
  }
  int n=lastUsedCell;
  final double index=q * totalWeight;
  if (index < 1) {
    return min;
  }
  if (weight[0] > 1 && index < weight[0] / 2) {
    return min + (index - 1) / (weight[0] / 2 - 1) * (mean[0] - min);
  }
  if (index > totalWeight - 1) {
    return max;
  }
  if (weight[n - 1] > 1 && totalWeight - index <= weight[n - 1] / 2) {
    return max - (totalWeight - index - 1) / (weight[n - 1] / 2 - 1) * (max - mean[n - 1]);
  }
  double weightSoFar=weight[0] / 2;
  for (int i=0; i < n - 1; i++) {
    double dw=(weight[i] + weight[i + 1]) / 2;
    if (weightSoFar + dw > index) {
      double leftUnit=0;
      if (weight[i] == 1) {
        if (index - weightSoFar < 0.5) {
          return mean[i];
        }
 else {
          leftUnit=0.5;
        }
      }
      double rightUnit=0;
      if (weight[i + 1] == 1) {
        if (weightSoFar + dw - index <= 0.5) {
          return mean[i + 1];
        }
        rightUnit=0.5;
      }
      double z1=index - weightSoFar - leftUnit;
      double z2=weightSoFar + dw - index - rightUnit;
      return weightedAverage(mean[i],z2,mean[i + 1],z1);
    }
    weightSoFar+=dw;
  }
  assert weight[n - 1] > 1;
  assert index <= totalWeight;
  assert index >= totalWeight - weight[n - 1] / 2;
  double z1=index - totalWeight - weight[n - 1] / 2.0;
  double z2=weight[n - 1] / 2 - z1;
  return weightedAverage(mean[n - 1],z1,max,z2);
}
