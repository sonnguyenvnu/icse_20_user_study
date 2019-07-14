/** 
 * Matches value against the set of rules using provided white/black list mode.
 */
public boolean match(final V value,final boolean blacklist){
  if (rules == null) {
    return blacklist;
  }
  boolean include=blacklist;
  if (include) {
    include=processExcludes(value,true);
    include=processIncludes(value,include);
  }
 else {
    include=processIncludes(value,false);
    include=processExcludes(value,include);
  }
  return include;
}
