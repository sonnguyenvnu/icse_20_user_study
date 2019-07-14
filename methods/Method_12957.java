public static String getFieldStringValue(BeanMap beanMap,String fieldName,String format){
  String cellValue=null;
  Object value=beanMap.get(fieldName);
  if (value != null) {
    if (value instanceof Date) {
      cellValue=TypeUtil.formatDate((Date)value,format);
    }
 else {
      cellValue=value.toString();
    }
  }
  return cellValue;
}
