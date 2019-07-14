public String getMessage(){
  String message=null;
  if (value == null || StringUtils.isEmpty(value.toString())) {
    message=requestAttribute + " is not present";
  }
 else {
    message=isExactMatch() ? null : requestAttribute + " does not match";
  }
  if (isUrlRegexPattern() && !anyQuestionsMarksAreEscaped(pattern.getExpected())) {
    message+=". When using a regex, \"?\" should be \"\\\\?\"";
  }
  if (pattern instanceof UrlPattern && pattern != UrlPattern.ANY && !pattern.getExpected().startsWith("/")) {
    message+=". URLs must start with a /";
  }
  return message;
}
