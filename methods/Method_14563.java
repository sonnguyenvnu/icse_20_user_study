private Row getAvailableRow(List<Row> currentRows,List<Row> newRows,int index){
  for (  Row row : currentRows) {
    if (row.getCell(index) == null) {
      return row;
    }
  }
  Row row=new Row(index);
  newRows.add(row);
  currentRows.add(row);
  return row;
}
