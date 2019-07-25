public static double cdf(final double x,double[] data){
  int n1=0;
  int n2=0;
  for (  Double v : data) {
    n1+=(v < x) ? 1 : 0;
    n2+=(v == x) ? 1 : 0;
  }
  return (n1 + n2 / 2.0) / data.length;
}
