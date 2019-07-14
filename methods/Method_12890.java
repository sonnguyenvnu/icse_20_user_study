/** 
 * @param f
 */
private void initOneColumnProperty(Field f){
  ExcelProperty p=f.getAnnotation(ExcelProperty.class);
  ExcelColumnProperty excelHeadProperty=null;
  if (p != null) {
    excelHeadProperty=new ExcelColumnProperty();
    excelHeadProperty.setField(f);
    excelHeadProperty.setHead(Arrays.asList(p.value()));
    excelHeadProperty.setIndex(p.index());
    excelHeadProperty.setFormat(p.format());
    excelColumnPropertyMap1.put(p.index(),excelHeadProperty);
  }
 else {
    ExcelColumnNum columnNum=f.getAnnotation(ExcelColumnNum.class);
    if (columnNum != null) {
      excelHeadProperty=new ExcelColumnProperty();
      excelHeadProperty.setField(f);
      excelHeadProperty.setIndex(columnNum.value());
      excelHeadProperty.setFormat(columnNum.format());
      excelColumnPropertyMap1.put(columnNum.value(),excelHeadProperty);
    }
  }
  if (excelHeadProperty != null) {
    this.columnPropertyList.add(excelHeadProperty);
  }
}
