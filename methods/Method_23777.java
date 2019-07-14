public long[] getLongColumn(int col){
  long[] outgoing=new long[rowCount];
  for (int row=0; row < rowCount; row++) {
    outgoing[row]=getLong(row,col);
  }
  return outgoing;
}
