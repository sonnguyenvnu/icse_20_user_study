/** 
 * ??????????
 * @param config            provider????consumer??
 * @param autoActiveFilters ????????????
 * @return ????????
 */
private static List<Filter> selectActualFilters(AbstractInterfaceConfig config,Map<String,ExtensionClass<Filter>> autoActiveFilters){
  List<Filter> customFilters=config.getFilterRef() == null ? new ArrayList<Filter>() : new CopyOnWriteArrayList<Filter>(config.getFilterRef());
  HashSet<String> excludes=parseExcludeFilter(customFilters);
  List<ExtensionClass<Filter>> extensionFilters=new ArrayList<ExtensionClass<Filter>>();
  List<String> filterAliases=config.getFilter();
  if (CommonUtils.isNotEmpty(filterAliases)) {
    for (    String filterAlias : filterAliases) {
      if (startsWithExcludePrefix(filterAlias)) {
        excludes.add(filterAlias.substring(1));
      }
 else {
        ExtensionClass<Filter> filter=EXTENSION_LOADER.getExtensionClass(filterAlias);
        if (filter != null) {
          extensionFilters.add(filter);
        }
      }
    }
  }
  if (!excludes.contains(StringUtils.ALL) && !excludes.contains(StringUtils.DEFAULT)) {
    for (    Map.Entry<String,ExtensionClass<Filter>> entry : autoActiveFilters.entrySet()) {
      if (!excludes.contains(entry.getKey())) {
        extensionFilters.add(entry.getValue());
      }
    }
  }
  if (extensionFilters.size() > 1) {
    Collections.sort(extensionFilters,new OrderedComparator<ExtensionClass<Filter>>());
  }
  List<Filter> actualFilters=new ArrayList<Filter>();
  for (  ExtensionClass<Filter> extensionFilter : extensionFilters) {
    actualFilters.add(extensionFilter.getExtInstance());
  }
  actualFilters.addAll(customFilters);
  return actualFilters;
}
