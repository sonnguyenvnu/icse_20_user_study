@Override public void addRow(List<CellData> cells,boolean isHeader){
  if (batchRequest == null) {
    batchRequest=new Request();
    rows=new ArrayList<RowData>(BATCH_SIZE);
  }
  List<com.google.api.services.sheets.v4.model.CellData> cellDatas=new ArrayList<>();
  RowData rowData=new RowData();
  for (int c=0; c < cells.size(); c++) {
    CellData cellData=cells.get(c);
    if (cellData != null && cellData.text != null) {
      cellDatas.add(cellData2sheetCellData(cellData));
    }
  }
  rowData.setValues(cellDatas);
  rows.add(rowData);
  row++;
  if (row % BATCH_SIZE == 0) {
    sendBatch(rows);
  }
}
