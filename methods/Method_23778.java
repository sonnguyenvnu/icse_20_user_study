public long[] getLongRow(int row){
  long[] outgoing=new long[columns.length];
  for (int col=0; col < columns.length; col++) {
    outgoing[col]=getLong(row,col);
  }
  return outgoing;
}
