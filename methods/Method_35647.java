public static RequestPatternBuilder requestMadeFor(String customMatcherName,Parameters parameters){
  return RequestPatternBuilder.forCustomMatcher(customMatcherName,parameters);
}
