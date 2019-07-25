/** 
 * Allows joining additional components to this builder using the given {@link Joiner} and {@link FormatRetention#ALL}. Simply executes the provided joiner on this instance to facilitate a chain pattern.
 * @param joiner joiner used for operation
 * @return this ComponentBuilder for chaining
 */
public ComponentBuilder append(Joiner joiner){
  return joiner.join(this,FormatRetention.ALL);
}
