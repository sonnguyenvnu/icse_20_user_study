public void addRows(Table source){
  int index=getRowCount();
  setRowCount(index + source.getRowCount());
  for (  TableRow row : source.rows()) {
    setRow(index++,row);
  }
}
