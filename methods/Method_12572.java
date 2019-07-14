protected boolean matchesPattern(String serviceId,Set<String> patterns){
  return patterns.stream().anyMatch(pattern -> PatternMatchUtils.simpleMatch(pattern,serviceId));
}
