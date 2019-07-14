public static java.sql.Date castToSqlDate(Object value){
  if (value == null) {
    return null;
  }
  if (value instanceof java.sql.Date) {
    return (java.sql.Date)value;
  }
  if (value instanceof java.util.Date) {
    return new java.sql.Date(((java.util.Date)value).getTime());
  }
  if (value instanceof Calendar) {
    return new java.sql.Date(((Calendar)value).getTimeInMillis());
  }
  long longValue=0;
  if (value instanceof BigDecimal) {
    longValue=longValue((BigDecimal)value);
  }
 else   if (value instanceof Number) {
    longValue=((Number)value).longValue();
  }
  if (value instanceof String) {
    String strVal=(String)value;
    if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
      return null;
    }
    if (isNumber(strVal)) {
      longValue=Long.parseLong(strVal);
    }
 else {
      JSONScanner scanner=new JSONScanner(strVal);
      if (scanner.scanISO8601DateIfMatch(false)) {
        longValue=scanner.getCalendar().getTime().getTime();
      }
 else {
        throw new JSONException("can not cast to Timestamp, value : " + strVal);
      }
    }
  }
  if (longValue <= 0) {
    throw new JSONException("can not cast to Date, value : " + value);
  }
  return new java.sql.Date(longValue);
}
