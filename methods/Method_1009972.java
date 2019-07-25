public static double quantile(final double q,double[] data){
  int n=data.length;
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
  return data[(int)Math.floor(index)];
}
