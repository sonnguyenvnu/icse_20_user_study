void methodCalled(String twitterUrl,long elapsedTime,boolean success){
  Matcher matcher=pattern.matcher(twitterUrl);
  if (matcher.matches() && matcher.groupCount() > 0) {
    String method=matcher.group(1);
    STATISTICS.methodCalled(method,elapsedTime,success);
  }
}
