public static java.sql.Timestamp castToTimestamp(Object value){
  if (value == null) {
    return null;
  }
  if (value instanceof Calendar) {
    return new java.sql.Timestamp(((Calendar)value).getTimeInMillis());
  }
  if (value instanceof java.sql.Timestamp) {
    return (java.sql.Timestamp)value;
  }
  if (value instanceof java.util.Date) {
    return new java.sql.Timestamp(((java.util.Date)value).getTime());
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
    if (strVal.endsWith(".000000000")) {
      strVal=strVal.substring(0,strVal.length() - 10);
    }
 else     if (strVal.endsWith(".000000")) {
      strVal=strVal.substring(0,strVal.length() - 7);
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
    throw new JSONException("can not cast to Timestamp, value : " + value);
  }
  return new java.sql.Timestamp(longValue);
}
