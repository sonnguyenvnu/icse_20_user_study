/** 
 * Customize the given  {@link PatternsRequestCondition} and prefix.
 * @param condition will never be {@literal null}.
 * @param prefix will never be {@literal null}.
 * @return
 */
protected PatternsRequestCondition customize(PatternsRequestCondition condition,String prefix){
  Set<String> patterns=condition.getPatterns();
  String[] augmentedPatterns=new String[patterns.size()];
  int count=0;
  for (  String pattern : patterns) {
    augmentedPatterns[count++]=prefix.concat(pattern);
  }
  return new PatternsRequestCondition(augmentedPatterns,getUrlPathHelper(),getPathMatcher(),useSuffixPatternMatch(),useTrailingSlashMatch(),getFileExtensions());
}
