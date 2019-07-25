/** 
 * Simple Cholesky decomposition, with no checks on squareness,  symmetricality, or positive definiteness. This follows the implementation from JAMA fairly closely. <p> Returns L such that LL' = A and L is lower-triangular.
 */
public static double[] cholesky(double[] input,int numRows){
  double[] result=new double[input.length];
  double sumRowSquared=0.0;
  double dotProduct=0.0;
  int rowOffset=0;
  int colOffset=0;
  for (int row=0; row < numRows; row++) {
    sumRowSquared=0.0;
    rowOffset=row * numRows;
    for (int col=0; col < row; col++) {
      dotProduct=0.0;
      colOffset=col * numRows;
      for (int i=0; i < col; i++) {
        dotProduct+=result[rowOffset + i] * result[colOffset + i];
      }
      result[rowOffset + col]=(input[rowOffset + col] - dotProduct) / result[colOffset + col];
      sumRowSquared+=result[rowOffset + col] * result[rowOffset + col];
    }
    result[rowOffset + row]=Math.sqrt(input[rowOffset + row] - sumRowSquared);
  }
  return result;
}
