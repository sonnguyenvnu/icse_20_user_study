/** 
 * ??????????????
 * @return
 */
public double[][] cube(){
  double[][] X=new double[m][n];
  for (int i=0; i < m; i++) {
    for (int j=0; j < n; j++) {
      X[i][j]=Math.pow(A[i][j],3.);
    }
  }
  return X;
}
