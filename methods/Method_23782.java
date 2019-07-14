public double[] getDoubleRow(int row){
  double[] outgoing=new double[columns.length];
  for (int col=0; col < columns.length; col++) {
    outgoing[col]=getDouble(row,col);
  }
  return outgoing;
}
