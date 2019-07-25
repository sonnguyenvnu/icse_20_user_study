/** 
 * Sets the path pattern that a  {@link Service} will be bound to.
 * @throws IllegalArgumentException if the specified path pattern is invalid
 */
public AbstractServiceBindingBuilder path(String pathPattern){
  defaultRouteBuilder=Route.builder().path(requireNonNull(pathPattern,"pathPattern"));
  return this;
}
