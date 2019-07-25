private static Double pearson(double[] xs,double[] ys){
  if (xs == null || ys == null || xs.length != ys.length) {
    return null;
  }
  double xMean=getMean(xs);
  double yMean=getMean(xs);
  int n=xs.length;
  double numerator=0;
  for (int i=0; i < n; i++) {
    numerator+=(xs[i] - xMean) * (ys[i] - yMean);
  }
  return numerator / (n * standardDeviation(xs) * standardDeviation(ys));
}
