public static boolean compare(Number number,Object target){
  if (number == target) {
    return true;
  }
  if (number == null || target == null) {
    return false;
  }
  if (target.equals(number)) {
    return true;
  }
  if (target instanceof Number) {
    return number.doubleValue() == ((Number)target).doubleValue();
  }
  if (target instanceof Date) {
    return number.longValue() == ((Date)target).getTime();
  }
  if (target instanceof String) {
    String stringValue=String.valueOf(target);
    if (DateFormatter.isSupport(stringValue)) {
      DateFormatter dateFormatter=DateFormatter.getFormatter(stringValue);
      return (dateFormatter.toString(new Date(number.longValue())).equals(stringValue));
    }
    try {
      return new BigDecimal(stringValue).doubleValue() == number.doubleValue();
    }
 catch (    NumberFormatException e) {
      return false;
    }
  }
  return false;
}
