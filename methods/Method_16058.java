public static boolean compare(Date date,Object target){
  if (date == target) {
    return true;
  }
  if (date == null || target == null) {
    return false;
  }
  if (target instanceof Date) {
    return date.getTime() == ((Date)target).getTime();
  }
  if (target instanceof String) {
    String stringValue=String.valueOf(target);
    if (DateFormatter.isSupport(stringValue)) {
      DateFormatter dateFormatter=DateFormatter.getFormatter(stringValue);
      return (dateFormatter.toString(date).equals(stringValue));
    }
  }
  if (target instanceof Number) {
    long longValue=((Number)target).longValue();
    return date.getTime() == longValue;
  }
  return false;
}
