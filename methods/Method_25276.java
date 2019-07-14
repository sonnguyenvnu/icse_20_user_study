/** 
 * Gets the flag value for a comma-separated set of enums of the given type, wrapped in an  {@link Optional}, which is empty if the flag is unset. If the flag is explicitly set to empty, an empty set will be returned.
 */
public <T extends Enum<T>>Optional<ImmutableSet<T>> getEnumSet(String key,Class<T> clazz){
  return this.get(key).map(value -> Streams.stream(Splitter.on(',').omitEmptyStrings().split(value)).map(v -> asEnumValue(key,v,clazz)).collect(toImmutableSet()));
}
