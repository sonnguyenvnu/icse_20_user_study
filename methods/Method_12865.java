public void initTableHead(){
  if (needHead && null != excelHeadProperty && !CollectionUtils.isEmpty(excelHeadProperty.getHead())) {
    int startRow=currentSheet.getLastRowNum();
    if (startRow > 0) {
      startRow+=4;
    }
 else {
      startRow=currentSheetParam.getStartRow();
    }
    addMergedRegionToCurrentSheet(startRow);
    int i=startRow;
    for (; i < this.excelHeadProperty.getRowNum() + startRow; i++) {
      Row row=WorkBookUtil.createRow(currentSheet,i);
      if (null != afterWriteHandler) {
        this.afterWriteHandler.row(i,row);
      }
      addOneRowOfHeadDataToExcel(row,this.excelHeadProperty.getHeadByRowNum(i - startRow));
    }
  }
}
