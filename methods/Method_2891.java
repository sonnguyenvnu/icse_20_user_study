/** 
 * Make a one-dimensional row packed copy of the internal array.
 * @return Matrix elements packed in a one-dimensional array by rows.
 */
public double[] getRowPackedCopy(){
  double[] vals=new double[m * n];
  for (int i=0; i < m; i++) {
    for (int j=0; j < n; j++) {
      vals[i * n + j]=A[i][j];
    }
  }
  return vals;
}
