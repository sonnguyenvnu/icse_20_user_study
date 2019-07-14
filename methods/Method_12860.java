@Override public void buildExcelHeadProperty(Class<? extends BaseRowModel> clazz,List<String> headOneRow){
  if (this.excelHeadProperty == null && (clazz != null || headOneRow != null)) {
    this.excelHeadProperty=new ExcelHeadProperty(clazz,new ArrayList<List<String>>());
  }
  if (this.excelHeadProperty.getHead() == null && headOneRow != null) {
    this.excelHeadProperty.appendOneRow(headOneRow);
  }
}
