/** 
 * ??????????????
 * @param customFilters ???filter??
 * @return ?????????key??
 */
private static HashSet<String> parseExcludeFilter(List<Filter> customFilters){
  HashSet<String> excludeKeys=new HashSet<String>();
  if (CommonUtils.isNotEmpty(customFilters)) {
    for (    Filter filter : customFilters) {
      if (filter instanceof ExcludeFilter) {
        ExcludeFilter excludeFilter=(ExcludeFilter)filter;
        String excludeName=excludeFilter.getExcludeName();
        if (StringUtils.isNotEmpty(excludeName)) {
          String excludeFilterName=startsWithExcludePrefix(excludeName) ? excludeName.substring(1) : excludeName;
          if (StringUtils.isNotEmpty(excludeFilterName)) {
            excludeKeys.add(excludeFilterName);
          }
        }
        customFilters.remove(filter);
      }
    }
  }
  if (!excludeKeys.isEmpty()) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Find exclude filters: {}",excludeKeys);
    }
  }
  return excludeKeys;
}
