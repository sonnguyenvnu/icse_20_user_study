@Override public void truncate(Session session){
  if (trace.isDebugEnabled()) {
    trace.debug("truncate");
  }
  removeAllRows();
  if (tableData.getContainsLargeObject()) {
    database.getLobStorage().removeAllForTable(table.getId());
  }
  tableData.setRowCount(0);
}
