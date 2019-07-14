static LinkedHashSet<CharSequence> extractUrls(String text){
  final Pattern urlPattern=Pattern.compile("(?:^|[\\W])((ht|f)tp(s?)://|www\\.)" + "(([\\w\\-]+\\.)+?([\\w\\-.~]+/?)*" + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]*$~@!:/{};']*)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
  LinkedHashSet<CharSequence> urlSet=new LinkedHashSet<>();
  Matcher matcher=urlPattern.matcher(text);
  while (matcher.find()) {
    int matchStart=matcher.start(1);
    int matchEnd=matcher.end();
    String url=text.substring(matchStart,matchEnd);
    urlSet.add(url);
  }
  return urlSet;
}
