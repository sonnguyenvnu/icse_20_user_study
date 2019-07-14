/** 
 * ???j????????
 * @param j
 * @return
 */
public Matrix col(int j){
  double[][] X=new double[m][1];
  for (int i=0; i < m; i++) {
    X[i][0]=A[i][j];
  }
  return new Matrix(X);
}
