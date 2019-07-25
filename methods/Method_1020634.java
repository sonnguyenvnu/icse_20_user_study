/** 
 * ??
 * @param value
 * @param valueStr
 * @param compare
 * @param type
 * @return
 */
private static boolean less(Object value,String valueStr,String compare,String type){
  if (type.equals("time") || type.equals("date")) {
    return DateUtil.convertDateObject(valueStr).compareTo(DateUtil.convertDateObject(compare)) < 0;
  }
  if (CommonUtils.isNumber(valueStr) && CommonUtils.isNumber(compare)) {
    return Double.parseDouble(valueStr) < Double.parseDouble(compare);
  }
 else   return valueStr.compareTo(compare) < 0;
}
