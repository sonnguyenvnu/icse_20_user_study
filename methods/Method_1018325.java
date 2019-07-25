/** 
 * Returns whether the given reference String matches the current  {@link Path}.
 * @param reference
 * @return
 */
public boolean matches(String reference){
  return reference == null ? false : this.path.matches(String.format(MATCH_PATTERN,Pattern.quote(reference)));
}
