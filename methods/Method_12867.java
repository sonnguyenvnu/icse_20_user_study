private void initTableStyle(com.alibaba.excel.metadata.TableStyle tableStyle){
  if (tableStyle != null) {
    this.currentHeadCellStyle=StyleUtil.buildCellStyle(this.workbook,tableStyle.getTableHeadFont(),tableStyle.getTableHeadBackGroundColor());
    this.currentContentCellStyle=StyleUtil.buildCellStyle(this.workbook,tableStyle.getTableContentFont(),tableStyle.getTableContentBackGroundColor());
  }
}
