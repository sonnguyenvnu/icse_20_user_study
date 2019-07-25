public double[] copy(int word){
  double[] result=new double[numColumns];
  for (int col=0; col < numColumns; col++) {
    result[col]=weights[word * stride + col];
  }
  return result;
}
