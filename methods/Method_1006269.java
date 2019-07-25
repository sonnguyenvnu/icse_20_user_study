/** 
 * Returns all nodes in this chain as separate keywords. E.g, for "A > B > C" we get {"A", "B", "C"}.
 */
public Set<Keyword> flatten(){
  return Stream.concat(Stream.of(this),OptionalUtil.toStream(getChild()).flatMap(child -> child.flatten().stream())).collect(Collectors.toSet());
}
