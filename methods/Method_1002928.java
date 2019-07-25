/** 
 * Puts the specified attribute into the Jetty  {@link Server}.
 * @see Server#setAttribute(String,Object)
 */
public JettyServiceBuilder attr(String name,Object attribute){
  attrs.put(requireNonNull(name,"name"),requireNonNull(attribute,"attribute"));
  return this;
}
