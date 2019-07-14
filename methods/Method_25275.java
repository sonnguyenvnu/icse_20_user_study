/** 
 * Gets the flag value for the given key as a Boolean, wrapped in an  {@link Optional}, which is empty if the flag is unset. <p>The value within the  {@link Optional} will be {@code true} if the flag's value is "true",{@code false} for "false", both case insensitive. If the value is neither "true" nor "false",throws an  {@link IllegalArgumentException}. <p>Note that any flag set without a value, e.g.  {@code -XepOpt:FlagValue}, will be "true".
 */
public Optional<Boolean> getBoolean(String key){
  return this.get(key).map(ErrorProneFlags::parseBoolean);
}
