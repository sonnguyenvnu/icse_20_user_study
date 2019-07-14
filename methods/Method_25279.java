/** 
 * Gets the flag value for the given key as a comma-separated  {@link Set} of Strings, wrapped inan  {@link Optional}, which is empty if the flag is unset. <p>(note: empty strings included, e.g.  {@code "-XepOpt:Set=,1,,1,2," => ["","1","2"]})
 */
public Optional<Set<String>> getSet(String key){
  return this.get(key).map(v -> ImmutableSet.copyOf(Splitter.on(',').split(v)));
}
