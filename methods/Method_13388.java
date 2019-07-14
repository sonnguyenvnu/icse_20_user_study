private String getMatchingPattern(String pattern,String lookupPath){
  if (pattern.equals(lookupPath)) {
    return pattern;
  }
  boolean hasSuffix=pattern.indexOf('.') != -1;
  if (!hasSuffix && this.pathMatcher.match(pattern + ".*",lookupPath)) {
    return pattern + ".*";
  }
  if (this.pathMatcher.match(pattern,lookupPath)) {
    return pattern;
  }
  if (!pattern.endsWith("/") && this.pathMatcher.match(pattern + "/",lookupPath)) {
    return pattern + "/";
  }
  return null;
}
