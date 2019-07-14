private String[] withNewPatterns(Set<String> patterns){
  return patterns.stream().map(pattern -> PathUtils.normalizePath(adminContextPath + pattern)).toArray(String[]::new);
}
