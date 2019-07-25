/** 
 * Sets the path pattern and  {@link HttpMethod#GET} that a {@link Service} will be bound to and support.
 * @throws IllegalArgumentException if the specified path pattern is invalid
 */
public AbstractServiceBindingBuilder get(String pathPattern){
  addRouteBuilder(pathPattern,EnumSet.of(GET));
  return this;
}
