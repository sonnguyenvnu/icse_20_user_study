private List<PathPattern> withNewPatterns(Set<PathPattern> patterns){
  return patterns.stream().map(pattern -> getPathPatternParser().parse(PathUtils.normalizePath(adminContextPath + pattern))).collect(toList());
}
