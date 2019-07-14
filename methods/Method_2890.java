/** 
 * Make a one-dimensional column packed copy of the internal array.
 * @return Matrix elements packed in a one-dimensional array by columns.
 */
public double[] getColumnPackedCopy(){
  double[] vals=new double[m * n];
  for (int i=0; i < m; i++) {
    for (int j=0; j < n; j++) {
      vals[i + j * m]=A[i][j];
    }
  }
  return vals;
}
