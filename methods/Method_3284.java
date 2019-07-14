public void pseudo_gradient(int size,double[] v,double[] x,double[] g,double C){
  for (int i=0; i < size; ++i) {
    if (x[i] == 0) {
      if (g[i] + C < 0) {
        v[i]=g[i] + C;
      }
 else       if (g[i] - C > 0) {
        v[i]=g[i] - C;
      }
 else {
        v[i]=0;
      }
    }
 else {
      v[i]=g[i] + C * Mcsrch.sigma(x[i]);
    }
  }
}
