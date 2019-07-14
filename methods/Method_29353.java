private static String matchCpuLine(String content){
  String result=EMPTY_STRING;
  if (content == null || EMPTY_STRING.equals(content.trim())) {
    return result;
  }
  Pattern pattern=Pattern.compile("(\\d+).(\\d+)");
  Matcher matcher=pattern.matcher(content);
  if (matcher.find()) {
    result=matcher.group();
  }
  return result;
}
