private void addOneRowOfHeadDataToExcel(Row row,List<String> headByRowNum){
  if (headByRowNum != null && headByRowNum.size() > 0) {
    for (int i=0; i < headByRowNum.size(); i++) {
      Cell cell=WorkBookUtil.createCell(row,i,getCurrentHeadCellStyle(),headByRowNum.get(i));
      if (null != afterWriteHandler) {
        this.afterWriteHandler.cell(i,cell);
      }
    }
  }
}
