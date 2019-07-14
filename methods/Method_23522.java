/** 
 * Make sure that the shape's matrix is 1) not null, and 2) has a matrix that can handle <em>at least</em> the specified number of dimensions.
 */
protected void checkMatrix(int dimensions){
  if (matrix == null) {
    if (dimensions == 2) {
      matrix=new PMatrix2D();
    }
 else {
      matrix=new PMatrix3D();
    }
  }
 else   if (dimensions == 3 && (matrix instanceof PMatrix2D)) {
    matrix=new PMatrix3D(matrix);
  }
}
