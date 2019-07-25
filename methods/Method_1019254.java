/** 
 * Returns the value of the option with the given key. 
 */
public final Optional<String> option(String key){
  return Optional.fromNullable(options().get(key));
}
