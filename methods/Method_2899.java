/** 
 * Generate identity matrix
 * @param m Number of rows.
 * @param n Number of colums.
 * @return An m-by-n matrix with ones on the diagonal and zeros elsewhere.
 */
public static Matrix identity(int m,int n){
  Matrix A=new Matrix(m,n);
  double[][] X=A.getArray();
  for (int i=0; i < m; i++) {
    for (int j=0; j < n; j++) {
      X[i][j]=(i == j ? 1.0 : 0.0);
    }
  }
  return A;
}
