static public void ensureColumnsInRowExist(List<String> columnNames,Row row){
  int count=row.cells.size();
  while (count > columnNames.size()) {
    columnNames.add("");
  }
}
