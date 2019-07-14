public static Date convert(String s){
  try {
    return DateUtils.fromISODateString(s);
  }
 catch (  InvalidFormatException e) {
    throw new RuntimeException("Could not parse date: " + s,e);
  }
}
