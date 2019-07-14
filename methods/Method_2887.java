/** 
 * Construct a matrix from a copy of a 2-D array.
 * @param A Two-dimensional array of doubles.
 * @throws IllegalArgumentException All rows must have the same length
 */
public static Matrix constructWithCopy(double[][] A){
  int m=A.length;
  int n=A[0].length;
  Matrix X=new Matrix(m,n);
  double[][] C=X.getArray();
  for (int i=0; i < m; i++) {
    if (A[i].length != n) {
      throw new IllegalArgumentException("All rows must have the same length.");
    }
    for (int j=0; j < n; j++) {
      C[i][j]=A[i][j];
    }
  }
  return X;
}
