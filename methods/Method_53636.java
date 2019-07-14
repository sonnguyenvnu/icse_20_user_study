static Cubic[] calcNaturalCubic(int n,double[] x){
  double[] gamma=new double[n];
  double[] delta=new double[n];
  double[] D=new double[n];
  n-=1;
  gamma[0]=1.0f / 2.0f;
  for (int i=1; i < n; i++) {
    gamma[i]=1 / (4 - gamma[i - 1]);
  }
  gamma[n]=1 / (2 - gamma[n - 1]);
  delta[0]=3 * (x[1] - x[0]) * gamma[0];
  for (int i=1; i < n; i++) {
    delta[i]=(3 * (x[i + 1] - x[i - 1]) - delta[i - 1]) * gamma[i];
  }
  delta[n]=(3 * (x[n] - x[n - 1]) - delta[n - 1]) * gamma[n];
  D[n]=delta[n];
  for (int i=n - 1; i >= 0; i--) {
    D[i]=delta[i] - gamma[i] * D[i + 1];
  }
  Cubic[] C=new Cubic[n];
  for (int i=0; i < n; i++) {
    C[i]=new Cubic((float)x[i],D[i],3 * (x[i + 1] - x[i]) - 2 * D[i] - D[i + 1],2 * (x[i] - x[i + 1]) + D[i] + D[i + 1]);
  }
  return C;
}
