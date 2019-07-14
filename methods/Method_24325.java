@Override protected void checkMatrix(int dimensions){
  if (matrix == null) {
    if (dimensions == 2) {
      matrix=new PMatrix2D();
      matrixInv=new PMatrix2D();
    }
 else {
      matrix=new PMatrix3D();
      matrixInv=new PMatrix3D();
    }
  }
 else   if (dimensions == 3 && (matrix instanceof PMatrix2D)) {
    matrix=new PMatrix3D(matrix);
    matrixInv=new PMatrix3D(matrixInv);
  }
}
