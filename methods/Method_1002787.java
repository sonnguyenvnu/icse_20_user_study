/** 
 * Sets the path pattern and  {@link HttpMethod#PUT} that a {@link Service} will be bound to and support.
 * @throws IllegalArgumentException if the specified path pattern is invalid
 */
public AbstractServiceBindingBuilder put(String pathPattern){
  addRouteBuilder(pathPattern,EnumSet.of(PUT));
  return this;
}
