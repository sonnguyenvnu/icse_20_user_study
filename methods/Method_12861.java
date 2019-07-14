/** 
 * @param sheet
 */
public void currentSheet(com.alibaba.excel.metadata.Sheet sheet){
  if (null == currentSheetParam || currentSheetParam.getSheetNo() != sheet.getSheetNo()) {
    cleanCurrentSheet();
    currentSheetParam=sheet;
    try {
      this.currentSheet=workbook.getSheetAt(sheet.getSheetNo() - 1);
    }
 catch (    Exception e) {
      this.currentSheet=WorkBookUtil.createSheet(workbook,sheet);
      if (null != afterWriteHandler) {
        this.afterWriteHandler.sheet(sheet.getSheetNo(),currentSheet);
      }
    }
    buildSheetStyle(currentSheet,sheet.getColumnWidthMap());
    this.initCurrentSheet(sheet);
  }
}
