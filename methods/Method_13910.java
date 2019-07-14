private com.google.api.services.sheets.v4.model.CellData cellData2sheetCellData(CellData cellData){
  com.google.api.services.sheets.v4.model.CellData sheetCellData=new com.google.api.services.sheets.v4.model.CellData();
  ExtendedValue ev=new ExtendedValue();
  ev.setStringValue(cellData.value.toString());
  sheetCellData.setUserEnteredValue(ev);
  return sheetCellData;
}
