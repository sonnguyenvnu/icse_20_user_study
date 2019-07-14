public int[] getIntRow(int row){
  int[] outgoing=new int[columns.length];
  for (int col=0; col < columns.length; col++) {
    outgoing[col]=getInt(row,col);
  }
  return outgoing;
}
