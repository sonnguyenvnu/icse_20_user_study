public double pchisq(double q,double df){
  double df2=df * .5;
  double q2=q * .5;
  int n=5, k;
  double tk, CFL, CFU, prob;
  if (q <= 0 || df <= 0)   throw new IllegalArgumentException("Illegal argument " + q + " or " + df + " for qnorm(p).");
  if (q < df) {
    tk=q2 * (1 - n - df2) / (df2 + 2 * n - 1 + n * q2 / (df2 + 2 * n));
    for (k=n - 1; k > 1; k--)     tk=q2 * (1 - k - df2) / (df2 + 2 * k - 1 + k * q2 / (df2 + 2 * k + tk));
    CFL=1 - q2 / (df2 + 1 + q2 / (df2 + 2 + tk));
    prob=Math.exp(df2 * Math.log(q2) - q2 - Maths.logGamma(df2 + 1) - Math.log(CFL));
  }
 else {
    tk=(n - df2) / (q2 + n);
    for (k=n - 1; k > 1; k--)     tk=(k - df2) / (q2 + k / (1 + tk));
    CFU=1 + (1 - df2) / (q2 + 1 / (1 + tk));
    prob=1 - Math.exp((df2 - 1) * Math.log(q2) - q2 - Maths.logGamma(df2) - Math.log(CFU));
  }
  return prob;
}
