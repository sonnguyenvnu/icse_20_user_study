public void currentTable(Table table){
  if (null == currentTable || currentTable.getTableNo() != table.getTableNo()) {
    cleanCurrentTable();
    this.currentTable=table;
    this.initExcelHeadProperty(table.getHead(),table.getClazz());
    this.initTableStyle(table.getTableStyle());
    this.initTableHead();
  }
}
