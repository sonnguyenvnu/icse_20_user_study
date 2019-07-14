private void addJavaObjectToExcel(Object oneRowData,Row row){
  int i=0;
  BeanMap beanMap=BeanMap.create(oneRowData);
  for (  ExcelColumnProperty excelHeadProperty : context.getExcelHeadProperty().getColumnPropertyList()) {
    BaseRowModel baseRowModel=(BaseRowModel)oneRowData;
    String cellValue=TypeUtil.getFieldStringValue(beanMap,excelHeadProperty.getField().getName(),excelHeadProperty.getFormat());
    CellStyle cellStyle=baseRowModel.getStyle(i) != null ? baseRowModel.getStyle(i) : context.getCurrentContentStyle();
    Cell cell=WorkBookUtil.createCell(row,i,cellStyle,cellValue,TypeUtil.isNum(excelHeadProperty.getField()));
    if (null != context.getAfterWriteHandler()) {
      context.getAfterWriteHandler().cell(i,cell);
    }
    i++;
  }
}
