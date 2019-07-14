public static String capitalizeSentence(final String sentence){
  String result=sentence;
  Matcher matcher=sWordPattern.matcher(result);
  while (matcher.find()) {
    String word=matcher.group();
    result=result.replace(matcher.group(),capitalize(word));
  }
  return result;
}
