public static Boolean isNum(Object cellValue){
  if (cellValue instanceof Integer || cellValue instanceof Double || cellValue instanceof Short || cellValue instanceof Long || cellValue instanceof Float || cellValue instanceof BigDecimal) {
    return true;
  }
  return false;
}
