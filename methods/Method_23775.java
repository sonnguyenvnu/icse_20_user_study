public int[] getIntColumn(int col){
  int[] outgoing=new int[rowCount];
  for (int row=0; row < rowCount; row++) {
    outgoing[row]=getInt(row,col);
  }
  return outgoing;
}
