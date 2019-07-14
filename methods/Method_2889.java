/** 
 * Copy the internal two-dimensional array.
 * @return Two-dimensional array copy of matrix elements.
 */
public double[][] getArrayCopy(){
  double[][] C=new double[m][n];
  for (int i=0; i < m; i++) {
    for (int j=0; j < n; j++) {
      C[i][j]=A[i][j];
    }
  }
  return C;
}
