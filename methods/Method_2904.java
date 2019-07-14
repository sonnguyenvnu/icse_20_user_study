/** 
 * ???i????????
 * @param i
 * @return
 */
public Matrix row(int i){
  double[][] X=new double[1][n];
  for (int j=0; j < n; j++) {
    X[0][j]=A[i][j];
  }
  return new Matrix(X);
}
