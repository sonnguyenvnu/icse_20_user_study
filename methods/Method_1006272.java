/** 
 * Returns whether this group matches the specified  {@link BibEntry} while taking the hierarchical informationinto account.
 */
public boolean matches(BibEntry entry){
  return getSearchMatcher().isMatch(entry);
}
