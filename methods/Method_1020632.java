/** 
 * ????????
 * @param value
 * @param compareType
 * @param compareValue
 * @return
 */
private static boolean compare(Object value,String compareType,String compareValue){
  String append="0";
  String[] calculateStr={"+","-"};
  for (  String calculate : calculateStr) {
    if (compareValue.indexOf(calculate) > 0) {
      String[] tmpAry=compareValue.split(calculate.equals("+") ? "\\+" : "\\-");
      append=calculate + tmpAry[1].trim();
      compareValue=tmpAry[0].trim();
      break;
    }
  }
  String type="string";
  String dayTimeFmt="yyyy-MM-dd HH:mm:ss";
  String dayFmt="yyyy-MM-dd";
  String lowCompareValue=compareValue.toLowerCase();
  if (lowCompareValue.equals("now()") || lowCompareValue.equals(".now") || lowCompareValue.equals("${.now}") || lowCompareValue.equals("nowtime()")) {
    compareValue=DateUtil.formatDate(DateUtil.addSecond(new Date(),Double.parseDouble(append)),dayTimeFmt);
    type="time";
  }
 else   if (lowCompareValue.equals("day()") || lowCompareValue.equals("sysdate()") || lowCompareValue.equals(".day") || lowCompareValue.equals(".day()") || lowCompareValue.equals("${.day}")) {
    compareValue=DateUtil.formatDate(DateUtil.addSecond(new Date(),Double.parseDouble(append)),dayFmt);
    type="date";
  }
  compareValue=compareValue.replaceAll("\'","").replaceAll("\"","");
  String realValue=value.toString();
  if (type.equals("time")) {
    realValue=DateUtil.formatDate(value,dayTimeFmt);
  }
 else   if (type.equals("date"))   realValue=DateUtil.formatDate(value,dayFmt);
  if (compareType.equals("==") || compareType.equals("=")) {
    return realValue.equalsIgnoreCase(compareValue);
  }
  if (compareType.equals("!=")) {
    return !realValue.equalsIgnoreCase(compareValue);
  }
  if (compareType.equals(">=")) {
    return moreEqual(value,realValue,compareValue,type);
  }
  if (compareType.equals("<=")) {
    return lessEqual(value,realValue,compareValue,type);
  }
  if (compareType.equals(">")) {
    return more(value,realValue,compareValue,type);
  }
  if (compareType.equals("<")) {
    return less(value,realValue,compareValue,type);
  }
  return true;
}
