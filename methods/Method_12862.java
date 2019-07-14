private void initCurrentSheet(com.alibaba.excel.metadata.Sheet sheet){
  initExcelHeadProperty(sheet.getHead(),sheet.getClazz());
  initTableStyle(sheet.getTableStyle());
  initTableHead();
}
