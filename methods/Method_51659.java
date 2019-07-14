/** 
 * Construct a String Filter using set of include and exclude regular expressions. If there are no include regular expressions provide, then a regular expression is added which matches every String by default. A String is included as long as the case that there is an include which matches or there is not an exclude which matches. <p> In other words, include patterns override exclude patterns.
 * @param includeRegexes The include regular expressions. May be <code>null</code>.
 * @param excludeRegexes The exclude regular expressions. May be <code>null</code>.
 * @return A String Filter.
 */
public static Filter<String> buildRegexFilterIncludeOverExclude(List<String> includeRegexes,List<String> excludeRegexes){
  OrFilter<String> includeFilter=new OrFilter<>();
  if (includeRegexes != null) {
    for (    String includeRegex : includeRegexes) {
      includeFilter.addFilter(new RegexStringFilter(includeRegex));
    }
  }
  OrFilter<String> excludeFilter=new OrFilter<>();
  if (excludeRegexes != null) {
    for (    String excludeRegex : excludeRegexes) {
      excludeFilter.addFilter(new RegexStringFilter(excludeRegex));
    }
  }
  return new OrFilter<>(includeFilter,new NotFilter<>(excludeFilter));
}
