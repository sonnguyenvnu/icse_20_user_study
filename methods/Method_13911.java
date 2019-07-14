private void sendBatch(List<RowData> rows){
  AppendCellsRequest acr=new AppendCellsRequest();
  acr.setFields("*");
  acr.setSheetId(0);
  acr.setRows(rows);
  batchRequest.setAppendCells(acr);
  requests.add(batchRequest);
}
