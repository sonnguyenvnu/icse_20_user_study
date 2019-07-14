@Override public void addRow(List<CellData> cells,boolean isHeader){
  if (isHeader) {
    columnNames=new ArrayList<String>(cells.size());
    for (    CellData cellData : cells) {
      columnNames.add(cellData.text);
    }
    try {
      tableId=FusionTableHandler.createTable(service,tableName,columnNames);
    }
 catch (    Exception e) {
      tableId=null;
      exceptions.add(e);
    }
  }
 else   if (tableId != null) {
    if (sbBatch == null) {
      sbBatch=new StringBuffer();
    }
    formatCsv(cells,sbBatch);
    rows++;
    if (rows % BATCH_SIZE == 0) {
      if (!sendBatch(BATCH_SIZE)) {
        return;
      }
    }
  }
}
