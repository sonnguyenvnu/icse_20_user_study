/** 
 * Returns the authority of the  {@link Request}.
 * @return the authority. {@code "?"} if the {@link Request} has failed even before its headers areproperly constructed.
 * @throws RequestLogAvailabilityException if this property is not available yet
 */
default String authority(){
  final String authority=requestHeaders().authority();
  assert authority != null;
  return authority;
}
