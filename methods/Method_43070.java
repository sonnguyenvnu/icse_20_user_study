private static Date fromRfc3339DateStringQuietly(String orderDate){
  try {
    return DateUtils.fromRfc3339DateString(orderDate);
  }
 catch (  InvalidFormatException e) {
    return null;
  }
}
