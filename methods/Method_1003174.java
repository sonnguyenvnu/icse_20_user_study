@Override public void truncate(Session session){
  rows=Utils.newSmallArrayList();
  firstFree=-1;
  if (tableData.getContainsLargeObject() && tableData.isPersistData()) {
    database.getLobStorage().removeAllForTable(table.getId());
  }
  tableData.setRowCount(0);
  rowCount=0;
}
