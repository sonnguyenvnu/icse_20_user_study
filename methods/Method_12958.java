public static Map getFieldValues(List<String> stringList,ExcelHeadProperty excelHeadProperty,Boolean use1904WindowDate){
  Map map=new HashMap();
  for (int i=0; i < stringList.size(); i++) {
    ExcelColumnProperty columnProperty=excelHeadProperty.getExcelColumnProperty(i);
    if (columnProperty != null) {
      Object value=TypeUtil.convert(stringList.get(i),columnProperty.getField(),columnProperty.getFormat(),use1904WindowDate);
      if (value != null) {
        map.put(columnProperty.getField().getName(),value);
      }
    }
  }
  return map;
}
