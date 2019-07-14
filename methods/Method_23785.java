public String[] getStringRow(int row){
  String[] outgoing=new String[columns.length];
  for (int col=0; col < columns.length; col++) {
    outgoing[col]=getString(row,col);
  }
  return outgoing;
}
