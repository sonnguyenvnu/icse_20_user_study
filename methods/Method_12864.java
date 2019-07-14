/** 
 * init excel header
 * @param head
 * @param clazz
 */
private void initExcelHeadProperty(List<List<String>> head,Class<? extends BaseRowModel> clazz){
  if (head != null || clazz != null) {
    this.excelHeadProperty=new ExcelHeadProperty(clazz,head);
  }
}
