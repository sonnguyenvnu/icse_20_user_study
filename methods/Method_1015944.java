/** 
 * HTTP PUT routing.
 * @param uriTemplate the specified request URI template
 * @param handler     the specified handler
 * @return router
 */
public static Router put(final String uriTemplate,final ContextHandler handler){
  return route().put(uriTemplate,handler);
}
