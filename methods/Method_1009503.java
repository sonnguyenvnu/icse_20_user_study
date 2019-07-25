public static double[][] transpose(double[][] matrix){
  int originalRows=matrix.length;
  int originalCols=matrix[0].length;
  int rows=originalCols;
  int cols=originalRows;
  double[][] result=new double[rows][cols];
  for (int row=0; row < originalRows; row++) {
    for (int col=0; col < originalCols; col++) {
      result[col][row]=matrix[row][col];
    }
  }
  return result;
}
