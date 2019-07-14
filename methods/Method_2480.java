/** 
 * Internal function used by GammaCdf
 * @param x
 * @param A
 * @return
 */
protected static double Gcf(double x,double A){
  double A0=0;
  double B0=1;
  double A1=1;
  double B1=x;
  double AOLD=0;
  double N=0;
  while (Math.abs((A1 - AOLD) / A1) > .00001) {
    AOLD=A1;
    N=N + 1;
    A0=A1 + (N - A) * A0;
    B0=B1 + (N - A) * B0;
    A1=x * A0 + N * A1;
    B1=x * B0 + N * B1;
    A0=A0 / B1;
    B0=B0 / B1;
    A1=A1 / B1;
    B1=1;
  }
  double Prob=Math.exp(A * Math.log(x) - x - LogGamma(A)) * A1;
  return 1.0 - Prob;
}
