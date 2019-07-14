protected void storeCell(List<Object[]> rows,int row,int col,Object value){
  while (row >= rows.size()) {
    rows.add(new Object[columns.size()]);
  }
  rows.get(row)[col]=value;
}
