/** 
 * Non-overlapping replacements, sorted in descending order by position. 
 */
public Set<Replacement> descending(){
  return new LinkedHashSet<>(replacements.values());
}
