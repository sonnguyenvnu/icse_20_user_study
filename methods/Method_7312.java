public static Integer parseInt(String value){
  if (value == null) {
    return 0;
  }
  Integer val=0;
  try {
    Matcher matcher=pattern.matcher(value);
    if (matcher.find()) {
      String num=matcher.group(0);
      val=Integer.parseInt(num);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return val;
}
