/** 
 * Pattern match our combined header matcher to the given collection of sorted request headers as a whole.
 * @param headers The headers.
 * @return True if the pattern matches or false if no match, no headers, or no matcher.
 */
public boolean match(SortedHeaderMap headers){
  if (headers != null && !headers.isEmpty() && sortedHeaderMatcher != null) {
    return sortedHeaderMatcher.reset(headers.joined()).find();
  }
  return false;
}
