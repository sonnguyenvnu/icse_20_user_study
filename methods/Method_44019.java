public static Date parseDate(String str){
  try {
    return str == null ? null : DateUtils.fromISODateString(str);
  }
 catch (  Exception e) {
    throw new RuntimeException("Could not parse date using '" + str + "'.",e);
  }
}
