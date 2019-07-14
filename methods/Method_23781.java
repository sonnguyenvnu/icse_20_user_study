public double[] getDoubleColumn(int col){
  double[] outgoing=new double[rowCount];
  for (int row=0; row < rowCount; row++) {
    outgoing[row]=getDouble(row,col);
  }
  return outgoing;
}
