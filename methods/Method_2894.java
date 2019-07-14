/** 
 * C = A + B
 * @param B another matrix
 * @return A + B
 */
public Matrix plus(Matrix B){
  checkMatrixDimensions(B);
  Matrix X=new Matrix(m,n);
  double[][] C=X.getArray();
  for (int i=0; i < m; i++) {
    for (int j=0; j < n; j++) {
      C[i][j]=A[i][j] + B.A[i][j];
    }
  }
  return X;
}
