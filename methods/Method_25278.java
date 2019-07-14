/** 
 * Gets the flag value for the given key as a comma-separated  {@link List} of Strings, wrapped inan  {@link Optional}, which is empty if the flag is unset. <p>(note: empty strings included, e.g.  {@code "-XepOpt:List=,1,,2," => ["","1","","2",""]})
 */
public Optional<List<String>> getList(String key){
  return this.get(key).map(v -> ImmutableList.copyOf(Splitter.on(',').split(v)));
}
