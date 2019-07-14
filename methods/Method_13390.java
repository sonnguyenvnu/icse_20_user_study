private static Set<String> prependLeadingSlash(String[] patterns){
  Set<String> result=new LinkedHashSet<>(patterns.length);
  for (  String pattern : patterns) {
    if (StringUtils.hasLength(pattern) && !pattern.startsWith("/")) {
      pattern="/" + pattern;
    }
    result.add(pattern);
  }
  return result;
}
