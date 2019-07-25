/** 
 * Sets the path pattern and  {@link HttpMethod#HEAD} that a {@link Service} will be bound to and support.
 * @throws IllegalArgumentException if the specified path pattern is invalid
 */
public AbstractServiceBindingBuilder head(String pathPattern){
  addRouteBuilder(pathPattern,EnumSet.of(HEAD));
  return this;
}
