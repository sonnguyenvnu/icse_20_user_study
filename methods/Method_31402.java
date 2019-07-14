private static String toRegexPattern(String... commands){
  return "^(" + StringUtils.arrayToDelimitedString("|",commands) + ")";
}
