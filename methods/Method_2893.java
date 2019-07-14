/** 
 * One norm
 * @return maximum column sum.
 */
public double norm1(){
  double f=0;
  for (int j=0; j < n; j++) {
    double s=0;
    for (int i=0; i < m; i++) {
      s+=Math.abs(A[i][j]);
    }
    f=Math.max(f,s);
  }
  return f;
}
