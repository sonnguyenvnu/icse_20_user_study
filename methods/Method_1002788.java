/** 
 * Sets the path pattern and  {@link HttpMethod#OPTIONS} that a {@link Service} will be bound to and support.
 * @throws IllegalArgumentException if the specified path pattern is invalid
 */
public AbstractServiceBindingBuilder options(String pathPattern){
  addRouteBuilder(pathPattern,EnumSet.of(OPTIONS));
  return this;
}
