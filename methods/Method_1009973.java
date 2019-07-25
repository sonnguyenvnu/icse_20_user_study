public static double quantile(final double q,List<Double> data){
  int n=data.size();
  if (n == 0) {
    return Double.NaN;
  }
  double index=q * n;
  if (index < 0) {
    index=0;
  }
  if (index > n - 1) {
    index=n - 1;
  }
  return data.get((int)Math.floor(index));
}
